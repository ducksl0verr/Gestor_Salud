package com.GrupoProga3.Gestor_Salud.features.Pago;

import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO.FacturaNueva;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO.FacturaRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Facturas.Servicio.IServicioFactura;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.features.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.features.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Pago.DTO.PagoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Pago.Mappers.PagoMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ServicioPago implements IServicioPago {

    private final RepositorioPago repositorioPago;
    private final RepositorioPaciente repositorioPaciente;
    private final PagoMapper pagoMapper;
    private final IServicioFactura servicioFactura;



    @Override
    @Transactional
    public PagoRespuesta guardar(PagoNuevo pagoNuevo) {
        List<Long> idsPacientes = pagoNuevo.idsPacientes() == null
                ? List.of()
                : pagoNuevo.idsPacientes().stream().distinct().toList();

        if (idsPacientes.isEmpty()) {
            throw new ReglaNegocioException("El pago debe tener al menos un paciente asociado.");
        }

        if (idsPacientes.size() > 1) {
            throw new ReglaNegocioException("El pago debe asociarse a un solo paciente.");
        }

        if (pagoNuevo.idsTurnos() == null || pagoNuevo.idsTurnos().isEmpty()) {
            throw new ReglaNegocioException("Debe seleccionar al menos un turno para pagar.");
        }

        List<EntidadPaciente> pacientes = repositorioPaciente.findAllById(idsPacientes);

        if (pacientes.size() != idsPacientes.size()) {
            throw new EntidadNoEncontradaException(
                    "Paciente",
                    "No encontrado",
                    1L,
                    "No se ha encontrado a ningún paciente con ID."
            );
        }

        // La factura se genera antes de guardar el pago: si algo falla,
        // la transacción revierte la factura y el estado de los turnos.
        FacturaRespuesta factura = servicioFactura.crearFacturaDesdePago(
                new FacturaNueva(pagoNuevo.idsTurnos()));

        if (!idsPacientes.get(0).equals(factura.idPaciente())) {
            throw new ReglaNegocioException(
                    "Los turnos seleccionados no pertenecen al paciente elegido.");
        }

        if (pagoNuevo.monto().compareTo(factura.total()) != 0) {
            throw new ReglaNegocioException(
                    "El monto del pago debe coincidir con el total de la factura generada ("
                            + factura.total() + ").");
        }

        // La fecha del pago siempre es la del momento en que se registra,
        // sin importar el valor que pueda enviar el cliente.
        LocalDateTime fechaPago = LocalDateTime.now();

        if (repositorioPago.existsByMontoAndFecha(factura.total(), fechaPago)) {
            throw new RecursoOcupadoException(
                    "Ya existe un pago con monto "
                            + factura.total()
                            + " y fecha "
                            + fechaPago
            );
        }

        EntidadPago pago = pagoMapper.toEntity(pagoNuevo);
        pago.setMonto(factura.total());
        pago.setFecha(fechaPago);
        pago.setPacientes(pacientes);

        EntidadPago guardado = repositorioPago.save(pago);

        return pagoMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public PagoRespuesta actualizar(Long id,
                                    PagoDTO pagoDTO) {

        EntidadPago pago = repositorioPago.findById(id)
                .orElseThrow(() ->
                        new EntidadNoEncontradaException(
                                "Pago",
                                "No encontrado",
                                id,
                                "No se ha encontrado ningún pago realizado con ese ID."
                        ));

        if (pagoDTO.monto() != null
                && pagoDTO.monto().compareTo(BigDecimal.ZERO) > 0) {

            pago.setMonto(pagoDTO.monto());
        }

        if (pagoDTO.fecha() != null) {
            pago.setFecha(pagoDTO.fecha());
        }

        EntidadPago actualizado = repositorioPago.save(pago);

        return pagoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {

        EntidadPago pago = repositorioPago.findById(id)
                .orElseThrow(() ->
                        new EntidadNoEncontradaException(
                                "Pago",
                                "No encontrado",
                                id,
                                "No se ha encontrado ningún pago realizado con ese ID."
                        ));

        repositorioPago.delete(pago);
    }


    public List<PagoRespuesta> buscarPagos(
            BigDecimal monto,
            LocalDateTime fecha) {

        if (monto != null && fecha != null) {
            return repositorioPago
                    .findByMontoAndFecha(monto, fecha)
                    .stream()
                    .map(pagoMapper::toDTO)
                    .toList();
        }

        if (monto != null) {
            return repositorioPago
                    .findByMonto(monto)
                    .stream()
                    .map(pagoMapper::toDTO)
                    .toList();
        }

        if (fecha != null) {
            return repositorioPago
                    .findByFecha(fecha)
                    .stream()
                    .map(pagoMapper::toDTO)
                    .toList();
        }

        return repositorioPago.findAll()
                .stream()
                .map(pagoMapper::toDTO)
                .toList();
    }
    @Override
    @Transactional(readOnly = true)
    public PagoRespuesta buscarPorId(Long id) {

        EntidadPago pago = repositorioPago.findById(id)
                .orElseThrow(() ->
                        new EntidadNoEncontradaException(
                                "Pago",
                                "No encontrado",
                                id,
                                "No se ha encontrado ningún pago realizado con ese ID."
                        ));

        return pagoMapper.toDTO(pago);
    }

    public List<PacienteRespuesta> buscarPacientesPorPago(Long idPago) {

        EntidadPago pago = repositorioPago.findById(idPago)
                .orElseThrow(() ->
                        new EntidadNoEncontradaException(
                                "Pago",
                                "No encontrado",
                                idPago,
                                "No se ha encontrado ningún pago realizado con ese ID."
                        ));

        return pago.getPacientes()
                .stream()
                .map(pagoMapper::toPacienteDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<PagoRespuesta> buscarTodos() {

        return repositorioPago.findAll()
                .stream()
                .map(pagoMapper::toDTO)
                .toList();
    }
}
