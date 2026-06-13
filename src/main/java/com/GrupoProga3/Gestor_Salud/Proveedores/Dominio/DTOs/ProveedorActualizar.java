package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import jakarta.validation.constraints.Email;
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
