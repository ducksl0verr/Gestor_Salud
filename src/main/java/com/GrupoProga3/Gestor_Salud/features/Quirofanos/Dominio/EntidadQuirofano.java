package com.GrupoProga3.Gestor_Salud.features.Quirofanos.Dominio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="quirofanos")
public class EntidadQuirofano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean disponible;
}
