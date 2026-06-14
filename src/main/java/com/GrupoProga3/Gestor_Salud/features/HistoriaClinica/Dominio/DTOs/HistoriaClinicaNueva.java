package com.GrupoProga3.Gestor_Salud.features.HistoriaClinica.Dominio.DTOs;

import java.time.LocalDate;
import java.util.List;

public record HistoriaClinicaNueva(LocalDate fecha,
                                   String observaciones,
                                   String evolucion,
                                   Long id_paciente,
                                   Long id_profesional,
                                   List<Long> ids_diagnosticos) {
}
