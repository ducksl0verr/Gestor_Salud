package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProveedorActualizar (@NotBlank
                                   String nombre,
                                   @NotBlank @Email
                                   String email,
                                   @NotBlank
                                   List<DomicilioNuevo> direccion) {
}
