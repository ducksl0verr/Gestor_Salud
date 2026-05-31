package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record HistoriaClinicaNueva(@DateTimeFormat @PastOrPresent Date fecha,
                                   @NotBlank String observaciones,
                                   @NotBlank String evolucion,
                                   @NotNull Long id_paciente,
                                   @NotNull Long id_profesional) {
}
