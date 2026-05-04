package com.GrupoProga3.Gestor_Salud.entidades.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Obras_Sociales")

public class Obras_Sociales {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name= "nombre", nullable = false, length =100)
    private String nombre;

    @Column (name= "cobertura", nullable = false, length=100)
    private String cobertura;



}
