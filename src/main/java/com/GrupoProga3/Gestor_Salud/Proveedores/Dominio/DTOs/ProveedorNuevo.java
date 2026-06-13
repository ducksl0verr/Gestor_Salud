package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Proveedores.Enums.TIPO_PROVEEDOR;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProveedorNuevo(@NotBlank String nombre,
                             @NotNull
                             ContactoNuevo contacto,
                             @NotBlank @Size(min = 11, max = 11, message = "El CUIL debe tener 11 caracteres")
                             String cuil,
                             @NotNull (message = "Debe poner al menos una direccion")
                             List<DomicilioNuevo> direccion,
                             @NotNull
                             TIPO_PROVEEDOR tipo) {
}
