package com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tratamientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadTratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

}
