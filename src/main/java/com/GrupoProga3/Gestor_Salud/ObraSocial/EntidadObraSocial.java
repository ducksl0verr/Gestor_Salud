package com.GrupoProga3.Gestor_Salud.ObraSocial;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name ="ObrasSociales")

public class EntidadObraSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La cobertura no puede estar vacía")
    @Size(min = 2, message = "La cobertura debe tener al menos 2 caracteres")
    @Column(name = "cobertura", nullable = false, length = 100)
    private String cobertura;
}

