package com.GrupoProga3.Gestor_Salud.Roles;

import com.GrupoProga3.Gestor_Salud.Roles.Dominio.DTOs.RolActualizar;
import com.GrupoProga3.Gestor_Salud.Roles.Dominio.DTOs.RolRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class ControladrorRol {
    private final IServicioRol servicioRol;

    @PutMapping("/{id}")
    ResponseEntity<RolRespuesta> actualizar (@PathVariable Long id,  @Valid @RequestBody RolActualizar rolActualizar){
        return ResponseEntity.ok(servicioRol.actualizar(id,rolActualizar));
    }
    @GetMapping
    ResponseEntity<List<RolRespuesta>> buscarTodos(){
        return ResponseEntity.ok(servicioRol.buscarTodos());
    }
    @GetMapping("/{id}")
    ResponseEntity<RolRespuesta> buscar(@PathVariable Long id){
        return ResponseEntity.ok(servicioRol.buscarPorId(id));
    }

}
