package com.GrupoProga3.Gestor_Salud.Pago.DTO;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PagoRespuesta(
        Long id,
        BigDecimal monto,
        LocalDateTime fecha,
        List<PacienteRespuesta> pacientes)
{}
