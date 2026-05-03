package com.GrupoProga3.Gestor_Salud;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @Entity
    @Table(name = "contactos")
    public class Contactos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        @Column(name = "nombre", nullable = false, length = 100)
        private String nombre;

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
        @Column(name = "apellido", nullable = false, length = 100)
        private String apellido;

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Pattern(
                regexp = "^[0-9+\\-\\s]{6,20}$",
                message = "El teléfono tiene un formato inválido"
        )
        @Column(name = "telefono", nullable = false, length = 20)
        private String telefono;
    }



