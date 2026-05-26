package com.GrupoProga3.Gestor_Salud.Pacientes.Controlador;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteDTO;
import com.GrupoProga3.Gestor_Salud.Pacientes.Servicio.IServicioPaciente;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pacientes")
public class ControladorPaciente {

    private IServicioPaciente servicioPaciente;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId


}
