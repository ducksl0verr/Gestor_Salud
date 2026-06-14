package com.GrupoProga3.Gestor_Salud.auth.permisos;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EntidadRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ROLES role;

    @ManyToMany(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
    @JoinTable(
            name="pemisos_role",
            joinColumns = @JoinColumn(name="id_role"),
            inverseJoinColumns = @JoinColumn(name="id_permiso")
    )
    private final Set<EntidadPermiso> permisos = new HashSet<>();

    public EntidadRole(ROLES nombre) {
        this.role = nombre;
    }

    public void agregarPermiso(EntidadPermiso p) {
        this.permisos.add(p);
    }
}
