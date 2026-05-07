package com.GrupoProga3.Gestor_Salud.entity.Proveedores;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedoresDTO {

    private Long idProvedor;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 150, message = "El email no puede superar los 150 caracteres")
    private String email;

    @NotBlank(message = "El CUIL no puede estar vacío")
    @Pattern(regexp = "\\d{11}", message = "El CUIL debe contener exactamente 11 dígitos numéricos")
    private String cuil;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 5, max = 255, message = "La dirección debe tener entre 5 y 255 caracteres")
    private String direccion;

    @NotNull(message = "El id del tipo de proveedor no puede ser nulo")
    @Positive(message = "El id del tipo de proveedor debe ser un valor positivo")
    private Long idTipoProvedor;
}
