package com.GrupoProga3.Gestor_Salud.features.Usuarios.Model;


import com.GrupoProga3.Gestor_Salud.auth.credenciales.EntidadCredencial;
import com.GrupoProga3.Gestor_Salud.auth.permisos.EntidadRole;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "usuarios")
public class EntidadUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido_usuario", nullable = false, length = 50)
    private String apellido;

    @Column(name = "matricula", length = 50)
    private String matricula;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_contacto")
    private EntidadContacto contacto;

    private String dni;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_role")
    private EntidadRole role;

    @OneToOne(mappedBy = "usuario")
    private EntidadCredencial credencial;

}

