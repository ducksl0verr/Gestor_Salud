package com.GrupoProga3.Gestor_Salud.Facturas.Servicio;

import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Model.EntidadDetalleFacturas;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaNueva;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.ENUMS.EstadoFactura;
import com.GrupoProga3.Gestor_Salud.Facturas.Dominio.Mapper.FacturaMapper;
import com.GrupoProga3.Gestor_Salud.Facturas.Excepciones.FacturaNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.Facturas.Model.EntidadFacturas;
import com.GrupoProga3.Gestor_Salud.Facturas.Repositorio.RepositorioFactura;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.ENUMS.EstadoFacturacionDeTurno;
import com.GrupoProga3.Gestor_Salud.Turno.Dominio.EntidadTurno;
import com.GrupoProga3.Gestor_Salud.Turno.RepositorioTurno;
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
        return repositorioFactura.findById(id).map(facturaMapper::toRespuestaDTO).orElseThrow(FacturaNoEncontradaException::new);
    }

    @Override
    public List<FacturaRespuesta> buscarTodos() {
        return repositorioFactura.findAll().stream()
                .map(facturaMapper::toRespuestaDTO)
                .toList();
    }

    @Transactional
    public FacturaRespuesta crearFactura(FacturaNueva facturaNueva){

        // Buscar  los turnos que ingreso el administrativo

        List<EntidadTurno> turnos = repositorioTurno.findAllById(facturaNueva.idsTurnos());

        // Validaado que existan los turnos que ingreso el administrativo

        if (turnos.isEmpty()) {
            throw new RuntimeException(
                    "No se encontraron turnos"
            );
        }

        if (turnos.size() != facturaNueva.idsTurnos().size()) { // esto es en el caso de que no se encuentre algun turno de la lista
            throw new RuntimeException(
                    "Uno o más turnos no existen"
            );
        }

        // Obtener paciente del primer turno
        EntidadPaciente paciente = turnos.get(0).getId_paciente();

        // Validar que todos sean del mismo paciente
        for (EntidadTurno turno : turnos) {

            if (!turno.getId_paciente()
                    .getId()
                    .equals(paciente.getId())) {

                throw new RuntimeException(
                        "Los turnos pertenecen a distintos pacientes"
                );
            }
        }

        // Validar que no estén facturados
        for (EntidadTurno turno : turnos) {

            if (turno.getEstadoFacturacionDeTurno().equals(EstadoFacturacionDeTurno.FACTURADO)) {

                throw new RuntimeException(
                        "El turno " + turno.getId() + " ya fue facturado"
                );
            }
        }

        // Crear la factura

        EntidadFacturas factura = new EntidadFacturas();
        factura.setPaciente(paciente);
        factura.setFechaEmision(LocalDate.now());
        factura.setFechaVencimiento(LocalDate.now().plusDays(30));
        factura.setEstadoFactura(EstadoFactura.PENDIENTE);
        factura.setNumero_factura(generarNumeroFactura());

        BigDecimal total = BigDecimal.ZERO;

        List<EntidadDetalleFacturas> detalles = new ArrayList<>();

        // Creacion de los detalles

        for (EntidadTurno turno : turnos)
        {
            BigDecimal precio = turno.getId_tratamiento().getPrecio();

            EntidadDetalleFacturas detalleFactura = new EntidadDetalleFacturas();

            detalleFactura.setFactura(factura);
            detalleFactura.setTurno(turno);
            detalleFactura.setConcepto(turno.getId_tratamiento().getNombre());
            detalleFactura.setCantidad(1L);
            detalleFactura.setImporte(precio);
            detalleFactura.setSubtotal(precio);

            detalles.add(detalleFactura);

            total = total.add(precio);

            // marcar como facturado
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
