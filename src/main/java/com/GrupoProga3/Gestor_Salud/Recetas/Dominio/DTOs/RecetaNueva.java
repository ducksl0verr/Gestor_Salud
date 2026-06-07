package com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.DTOs.DetalleRecetaNuevo;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record RecetaNueva(@NotNull
                          Long id_paciente,
                          @NotNull
                          Long id_profesional,
                          List<DetalleRecetaNuevo> detalles) {
}
