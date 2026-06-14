package com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProveedorActualizar (@NotBlank
                                   String nombre,
                                   @NotNull
                                   ContactoNuevo contacto,
                                   @NotNull
                                   List<DomicilioNuevo> direccion) {
}
