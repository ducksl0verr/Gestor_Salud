package com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="diagnosticos")
public class EntidadDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id; ///Pienso que deberiamos cambiar el tipo de ID, para que distintos pacientes tengan un mismo diagnostico
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
}
