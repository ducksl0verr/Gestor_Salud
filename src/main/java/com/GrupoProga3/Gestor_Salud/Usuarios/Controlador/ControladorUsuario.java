package com.GrupoProga3.Gestor_Salud.Usuarios.Controlador;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.ProfesionalDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Servicio.IServicioUsuario;
import jakarta.validation.Valid;
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

    @GetMapping("/profesionales")
    public ResponseEntity<List<ProfesionalDTO>> buscarTodosProfesionales()
    {
        return ResponseEntity.ok(servicioUsuario.buscarTodosProfesionales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId (@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioUsuario.buscarPorId(id));
    }

    @GetMapping("/profesionales/{id}")
    public ResponseEntity<ProfesionalDTO> buscarPorIdProfesionales(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioUsuario.buscarPorIdProfesional(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> guardar (@RequestBody @Valid UsuarioDTO usuarioDTO)
    {
        return new ResponseEntity<>(servicioUsuario.guardar(usuarioDTO), HttpStatus.CREATED);
    }

    @PostMapping("/profesionales")
    public ResponseEntity<ProfesionalDTO> guardarProfesional(@RequestBody @Valid ProfesionalDTO profesionalDTO)
    {
        return new ResponseEntity<>(servicioUsuario.guardarProfesional(profesionalDTO),HttpStatus.CREATED);
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

    @PutMapping("/profesionales/{id}")
    public ResponseEntity<ProfesionalDTO> actualizarProfesional(@PathVariable Long id, @RequestBody @Valid ProfesionalDTO profesionalDTO)
    {
        return ResponseEntity.ok(servicioUsuario.actualizarProfesional(id,profesionalDTO));
    }

}
