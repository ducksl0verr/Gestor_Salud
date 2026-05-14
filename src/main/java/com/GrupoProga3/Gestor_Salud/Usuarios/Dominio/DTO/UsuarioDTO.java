package com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String matricula; // posible cambio debido a que puede ser null si no es profesional. VER
    private String email;
    private Long id_domicilio;


}
