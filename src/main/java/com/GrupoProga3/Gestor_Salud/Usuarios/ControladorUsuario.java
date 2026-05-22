package com.GrupoProga3.Gestor_Salud.Usuarios;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/usuarios")
public class ControladorUsuario {

    private final IServicioUsuario servicioUsuario;


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos ()
    {
        return ResponseEntity.ok(servicioUsuario.buscarTodos());
    }

    @GetMapping("/id/{id}")
    public




}
