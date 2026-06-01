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
    private Long id; ///Pienso que deberiamos cambiar el tipo de ID, para que distintos pacientes tengan un mismo diagnostico
    private String nombre;
    private String descripcion;
}
