package com.GrupoProga3.Gestor_Salud.Usuarios;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class ControladorUsuario {

    private final IServicioUsuario servicioUsuario;


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos ()
    {
        return ResponseEntity.ok(servicioUsuario.buscarTodos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId (@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioUsuario.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> guardar (@RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        return new ResponseEntity<>(servicioUsuario.guardar(usuarioDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar (@PathVariable Long id)
    {
        servicioUsuario.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO)
    {
       return  ResponseEntity.ok(servicioUsuario.actualizar(id, usuarioDTO));
    }

}
