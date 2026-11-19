package com.GrupoProga3.Gestor_Salud.features.Facturas.Servicio;

import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Model.EntidadDetalleFacturas;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO.FacturaNueva;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.ENUMS.EstadoFactura;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.Mapper.FacturaMapper;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Model.EntidadFacturas;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Repositorio.RepositorioFactura;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.ENUMS.EstadoTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.Dominio.EntidadTurno;
import com.GrupoProga3.Gestor_Salud.features.Turno.RepositorioTurno;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioFactura implements IServicioFactura{

    private final RepositorioFactura repositorioFactura;
    private final RepositorioTurno repositorioTurno;
    private final FacturaMapper facturaMapper;

    @Override
    public FacturaRespuesta buscarPorId(Long id) {
        return repositorioFactura.findById(id).map(facturaMapper::toRespuestaDTO)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Factura",
                        "No encontrada",
                        id,
                        "No se ha encontrado ninguna factura con aquel ID."
                ));
    }

    @Override
    public List<FacturaRespuesta> buscarTodos() {
        return repositorioFactura.findAll().stream()
                .map(facturaMapper::toRespuestaDTO)
                .toList();
    }

    @Override
    @Transactional
    public FacturaRespuesta crearFactura(FacturaNueva facturaNueva) {
        return crear(facturaNueva, EstadoFactura.PENDIENTE);
    }

    @Override
    @Transactional
    public FacturaRespuesta crearFacturaDesdePago(FacturaNueva facturaNueva) {
        // Existe un pago real que la respalda, por eso nace como REALIZADA.
        return crear(facturaNueva, EstadoFactura.REALIZADA);
    }

    private FacturaRespuesta crear(FacturaNueva facturaNueva, EstadoFactura estadoFactura) {
        if (facturaNueva == null || facturaNueva.idsTurnos() == null
                || facturaNueva.idsTurnos().isEmpty()) {
            throw new ReglaNegocioException("Debe seleccionar al menos un turno para facturar.");
        }

        List<Long> idsTurnos = facturaNueva.idsTurnos().stream().distinct().toList();

        List<EntidadTurno> turnos = repositorioTurno.findAllById(idsTurnos);

        if (turnos.isEmpty()) {
            throw new EntidadNoEncontradaException("Turnos",
                    "No encontrado",
                    1L,
                    "No se ha encontrado ningún turno.");
        }

        if (turnos.size() != idsTurnos.size()) {
            throw new EntidadNoEncontradaException("Turnos",
                    "No encontrado",
                    1L,
                    "No se ha encontrado ningún turno.");
        }

        EntidadPaciente paciente = turnos.get(0).getPaciente();

        if (paciente == null) {
            throw new ReglaNegocioException("Los turnos seleccionados no tienen un paciente asociado.");
        }

        for (EntidadTurno turno : turnos) {
            if (turno.getPaciente() == null
                    || !turno.getPaciente().getId().equals(paciente.getId())) {
                throw new RecursoOcupadoException("Los turnos pertenecen a distintos pacientes");
            }

            if (Boolean.TRUE.equals(turno.getEliminado())
                    || EstadoTurno.CANCELADO.equals(turno.getEstadoTurno())) {
                throw new ReglaNegocioException(
                        "No se puede facturar el turno " + turno.getId() + " porque está cancelado.");
            }

            if (EstadoFacturacionDeTurno.FACTURADO.equals(turno.getEstadoFacturacionDeTurno())) {
                throw new RecursoOcupadoException(
                        "El turno " + turno.getId() + " ya fue facturado");
            }

            if (turno.getTratamiento() == null
                    || turno.getTratamiento().getPrecio() == null
                    || turno.getTratamiento().getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ReglaNegocioException(
                        "El turno " + turno.getId() + " no tiene un tratamiento con precio válido para facturar.");
            }
        }

        EntidadFacturas factura = new EntidadFacturas();
        factura.setPaciente(paciente);
        factura.setObraSocial(paciente.getObraSocial());
        factura.setFechaEmision(LocalDate.now());
        factura.setFechaVencimiento(LocalDate.now().plusDays(30));
        factura.setEstadoFactura(estadoFactura);
        factura.setNumero_factura(generarNumeroFactura());

        BigDecimal total = BigDecimal.ZERO;
        List<EntidadDetalleFacturas> detalles = new ArrayList<>();

        for (EntidadTurno turno : turnos) {
            BigDecimal precio = turno.getTratamiento().getPrecio();

            EntidadDetalleFacturas detalleFactura = new EntidadDetalleFacturas();
            detalleFactura.setFactura(factura);
            detalleFactura.setTurno(turno);
            detalleFactura.setConcepto(turno.getTratamiento().getNombre());
            detalleFactura.setCantidad(1L);
            detalleFactura.setImporte(precio);
            detalleFactura.setSubtotal(precio);

            detalles.add(detalleFactura);
            total = total.add(precio);

            // El turno recién queda facturado cuando ya existe el pago que respalda esta factura.
            turno.setEstadoFacturacionDeTurno(EstadoFacturacionDeTurno.FACTURADO);
        }

        factura.setDetalles(detalles);
        factura.setTotal(total);

        EntidadFacturas facturaGuardada = repositorioFactura.save(factura);
        return facturaMapper.toRespuestaDTO(facturaGuardada);
    }

    private String generarNumeroFactura() {
        return "FAC-" + System.currentTimeMillis();
    }

}
