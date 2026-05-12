package com.GrupoProga3.Gestor_Salud.Usuarios;


import com.GrupoProga3.Gestor_Salud.Domicilio.EntidadDomicilio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")

public class EntidadUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name = "apellido_usuario", nullable = false, length = 50)
    private String apellido;

    @NotBlank(message = "El dni no puede estar vacío")
    @Pattern(regexp = "\\d{7,8}", message = "DNI inválido")
    @Column(name = "dni",nullable = false)
    private String dni;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(
            regexp = "^[0-9+\\-\\s]{6,20}$",
            message = "El teléfono tiene un formato inválido"
    )
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "matricula", length = 50)
    private String matricula;

    @NotBlank(message = "El mail no puede estar vacío")
    @Column(name = "email", nullable = false, length = 50)
    private String email;



    //RELACIONES
    /// Aca segun por lo que entiendo, un domicilio pueden tener muchos usuarios pero un usuario podra tener un solo domicilio

    @ManyToOne

    @JoinColumn(name = "id_domicilio")
    private EntidadDomicilio domicilio;

}



//ANOTACIONES PARA EL FUTURO
///   es necesario una validacion en los emails. Acepta cosas como @maxi.c2 o maxi@ etc... VER DESPUES

///  supuestamente se debe de usar import jakarta.validation.constraints.Email;
/// import jakarta.validation.constraints.NotBlank;

/// Y en tu atributo:
///
/// @NotBlank(message = "El mail no puede estar vacío")
/// @Email(message = "Formato de email inválido")
/// @Column(name = "email", nullable = false, length = 50)
/// private String email;



/// ¿Qué hace cada uno?
///
/// @NotBlank → evita null, "" o " "
/// @Email → valida formato tipo email
///
/// Acepta:
///
/// maxi@gmail.com
/// juan.perez@hotmail.com
/// test123@empresa.com.ar
///
/// Rechaza:
///
/// maxi
/// maxi@
/// @gmail.com
/// maxi.gmail.com
///
/// Importante: para que Spring realmente valide cuando recibís requests, en tu controller o service necesitás usar @Valid.
///
/// Ejemplo:
///
/// @PostMapping
/// public ResponseEntity<?> crearUsuario(@Valid @RequestBody EntidadUsuario usuario) {
///     return ResponseEntity.ok(usuarioService.guardar(usuario));
/// }
///
/// Sin @Valid, tus anotaciones están de adorno decorativo 😄.
///
/// Entonces checklist:
///
/// @Email en el campo ✅
/// @Valid en el controller ✅
///
/// Con eso ya valida solo.