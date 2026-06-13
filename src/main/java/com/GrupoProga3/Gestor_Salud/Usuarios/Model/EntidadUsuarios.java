package com.GrupoProga3.Gestor_Salud.Usuarios.Model;


import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Roles.Dominio.EntidadRol;
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

    @Column(name = "dni",nullable = false)
    private String dni;

    //@Column(name = "telefono", nullable = false, length = 20)
    //private String telefono;

    @Column(name = "matricula", length = 50)
    private String matricula;

    //@Column(name = "email", nullable = false, length = 50)
    //private String email;

    //RELACIONES
    /*@ManyToOne
    @JoinColumn(name = "id_domicilio")
    private EntidadDomicilio domicilio;
     */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_contacto")
    private EntidadContacto contacto;

}

