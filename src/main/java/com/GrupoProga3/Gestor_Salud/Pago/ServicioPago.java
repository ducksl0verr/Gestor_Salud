package com.GrupoProga3.Gestor_Salud.Pago;

import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoDTO;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoNuevo;
import com.GrupoProga3.Gestor_Salud.Pago.DTO.PagoRespuesta;
import com.GrupoProga3.Gestor_Salud.Pago.Mappers.PagoMapper;
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
    private final PagoMapper pagoMapper;



    @Override
    @Transactional
    public PagoRespuesta guardar(PagoNuevo pagoNuevo) {
        if (repositorioPago.existsByMontoAndFecha(
                pagoNuevo.monto(),
                pagoNuevo.fecha())) {

            throw new RecursoOcupadoException(
                    "Ya existe un pago con monto "
                            + pagoNuevo.monto()
                            + " y fecha "
                            + pagoNuevo.fecha()
            );
        }

        if (pagoNuevo.idsPacientes() == null || pagoNuevo.idsPacientes().isEmpty()) {
            throw new ReglaNegocioException(
                    "El pago debe tener al menos un paciente asociado."
            );
        }

        EntidadPago pago = pagoMapper.toEntity(pagoNuevo);

        List<EntidadPago> pacientes =
                repositorioPago.findAllById(pagoNuevo.idsPacientes());

        if (pacientes.size() != pagoNuevo.idsPacientes().size()) {
            throw new EntidadNoEncontradaException(
                    "Pago",
                    "No encontrado",
                    1l,
                    "No se ha encontrado a ningún pago con ID."
            );
        }

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
