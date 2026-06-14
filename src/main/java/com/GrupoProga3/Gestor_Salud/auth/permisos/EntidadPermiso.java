package com.GrupoProga3.Gestor_Salud.auth.permisos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntidadPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="permiso", nullable=false, unique=true)
    private PERMISOS permiso;
}
