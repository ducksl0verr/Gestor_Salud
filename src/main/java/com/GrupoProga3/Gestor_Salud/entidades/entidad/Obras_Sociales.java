package com.GrupoProga3.Gestor_Salud.entidades.entidad;

import jakarta.persistence.*;

@Entity
@Table

public class Obras_Sociales {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name= "nombre", nullable = false, length =100)
    private String nombre;
    @Column (name= "cobertura", nullable = false, length=100)
    private String cobertura;



}
