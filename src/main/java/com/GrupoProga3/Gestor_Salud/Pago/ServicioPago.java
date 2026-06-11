package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoExistenteException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoRespuesta;
import com.GrupoProga3.Gestor_Salud.Pago.Mappers.PagoMapper;
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
    private final PagoMapper pagoMapper;



    @Override
    @Transactional
    public PagoRespuesta guardar(PagoNuevo pagoNuevo) {
        if (repositorioPago.existsByMontoAndFecha(
                pagoNuevo.monto(),
                pagoNuevo.fecha())) {

            throw new RecursoExistenteException(
                    "Ya existe un pago con monto "
                            + pagoNuevo.monto()
                            + " y fecha "
                            + pagoNuevo.fecha()
            );
        }
        if (pagoNuevo.pacientes() == null
                || pagoNuevo.pacientes().isEmpty()) {

            throw new ReglaNegocioException(
                    "El pago debe tener al menos un paciente asociado."
            );
        }

        EntidadPago pago = pagoMapper.toEntity(pagoNuevo);

        if (pago.getFecha() == null) {
            pago.setFecha(LocalDateTime.now());
        }

        EntidadPago guardado = repositorioPago.save(pago);

        return pagoMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public PagoRespuesta actualizar(Long id,
                                    PagoDTO pagoDTO) {

        EntidadPago pago = repositorioPago.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "El pago con id: " + id + " no existe."
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
                        new RecursoNoEncontradoException(
                                "El pago con id: " + id + " no existe."
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
                        new RecursoNoEncontradoException(
                                "El pago con id: " + id + " no existe."
                        ));

        return pagoMapper.toDTO(pago);
    }

    public List<PacienteRespuesta> buscarPacientesPorPago(Long idPago) {

        EntidadPago pago = repositorioPago.findById(idPago)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "El pago con id: " + idPago + " no existe."
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
