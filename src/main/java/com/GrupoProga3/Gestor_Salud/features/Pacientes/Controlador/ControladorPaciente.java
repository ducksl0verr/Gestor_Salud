package com.GrupoProga3.Gestor_Salud.features.Pacientes.Controlador;

import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteActualizar;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Pacientes.Servicio.IServicioPaciente;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pacientes")
public class ControladorPaciente {

    private IServicioPaciente servicioPaciente;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_PACIENTE')")
    public ResponseEntity<PacienteRespuesta> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioPaciente.buscarPorid(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_PACIENTE')")
    public ResponseEntity<List<PacienteRespuesta>> buscarTodos()
    {
        return ResponseEntity.ok(servicioPaciente.buscarTodos());
    }


    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_PACIENTE')")
    public ResponseEntity<PacienteRespuesta> guardar (@RequestBody @Valid PacienteNuevo pacienteNuevo)
    {
        return new ResponseEntity<>(servicioPaciente.guardar(pacienteNuevo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ELIMINAR_PACIENTE')")
    public ResponseEntity<Void> borrar(@PathVariable Long id)
    {
        servicioPaciente.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_PACIENTE')")
    public ResponseEntity<PacienteRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid PacienteActualizar pacienteActualizar)
    {
        return ResponseEntity.ok(servicioPaciente.actualizar(id, pacienteActualizar));
    }








}
