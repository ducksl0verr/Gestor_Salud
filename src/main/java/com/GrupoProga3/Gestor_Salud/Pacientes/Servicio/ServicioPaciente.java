package com.GrupoProga3.Gestor_Salud.Pacientes.Servicio;

import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.DTO.PacienteDTO;
import com.GrupoProga3.Gestor_Salud.Pacientes.Dominio.Mapper.PacienteMapper;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioPaciente implements IServicioPaciente{

    private final RepositorioPaciente repositorioPaciente;
    private final PacienteMapper pacienteMapper;

    @Override
    public PacienteDTO guardar(PacienteDTO pacienteDTO) {
        EntidadPaciente guardado = repositorioPaciente.save(pacienteMapper.toEntity(pacienteDTO));

        return pacienteMapper.toDTO(guardado);
    }

    @Override
    public void borrar(Long id) {
        repositorioPaciente.findById(id)
                .ifPresent(repositorioPaciente::delete);
    }

    @Override
    public PacienteDTO actualizar(Long id, PacienteDTO pacienteDTO) {
        EntidadPaciente pac = repositorioPaciente.findById(id)
                .orElseThrow();

        pac.setNombre(pacienteDTO.nombre());
        pac.setApellido(pacienteDTO.apellido());
        pac.setFecha_nacimiento(pacienteDTO.fecha_nacimiento());

        EntidadPaciente actualizado = repositorioPaciente.save(pac);
        return pacienteMapper.toDTO(actualizado);
    }

    @Override
    public PacienteDTO buscarPorid(Long id) {
        return repositorioPaciente.findById(id)
                .map(pacienteMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        return repositorioPaciente.findAll().stream()
                .map(pacienteMapper::toDTO)
                .toList();
    }

}
