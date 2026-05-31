package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs;

import java.util.Date;

public record HistoriaClinicaNueva(Date fecha,
                                   String observaciones,
                                   String evolucion,
                                   Long id_paciente,
                                   Long id_profesional) {
}
