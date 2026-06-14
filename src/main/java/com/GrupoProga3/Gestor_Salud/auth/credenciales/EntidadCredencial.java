package com.GrupoProga3.Gestor_Salud.auth.credenciales;

import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.features.Usuarios.Model.EntidadUsuarios;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntidadCredencial implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean enabled;

    @Column(name="refresh_token", length = 2048, unique = true)
    private String refreshToken;

    @OneToOne
    @JoinColumn(name="id_usuario", referencedColumnName = "id", unique = true)
    private EntidadUsuarios usuario;

    @ManyToOne
    @JoinColumn(name="id_role")
    private EntidadRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(role.getRole().name())
        );
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.enabled);
    }
}
