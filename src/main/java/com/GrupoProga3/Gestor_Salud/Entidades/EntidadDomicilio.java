package com.GrupoProga3.Gestor_Salud.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Domicilios")
public class EntidadDomicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id_domicilio;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String calle;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String numero;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String piso;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String depto;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String localidad;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String provincia;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String codigo_postal;
    @Column(length=50, nullable=false)
    @NotNull
    @NotBlank
    private String pais;
}
