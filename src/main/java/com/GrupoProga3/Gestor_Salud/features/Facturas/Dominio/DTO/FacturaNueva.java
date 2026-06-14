package com.GrupoProga3.Gestor_Salud.features.Facturas.Dominio.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FacturaNueva(@NotEmpty List<@NotNull Long> idsTurnos){}



