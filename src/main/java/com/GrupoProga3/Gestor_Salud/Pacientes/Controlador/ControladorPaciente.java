package com.GrupoProga3.Gestor_Salud.Pacientes.Controlador;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteActualizar;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteNuevo;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteRespuesta;
import com.GrupoProga3.Gestor_Salud.Pacientes.Servicio.IServicioPaciente;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pacientes")
public class ControladorPaciente {

    private IServicioPaciente servicioPaciente;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteRespuesta> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(servicioPaciente.buscarPorid(id));
    }

    @GetMapping
    public ResponseEntity<List<PacienteRespuesta>> buscarTodos()
    {
        return ResponseEntity.ok(servicioPaciente.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<PacienteRespuesta> guardar (@RequestBody @Valid PacienteNuevo pacienteNuevo)
    {
        return new ResponseEntity<>(servicioPaciente.guardar(pacienteNuevo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id)
    {
        servicioPaciente.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteRespuesta> actualizar (@PathVariable Long id, @RequestBody @Valid PacienteActualizar pacienteNuevo)
    {
        return ResponseEntity.ok(servicioPaciente.actualizar(id, pacienteNuevo));
    }








}
