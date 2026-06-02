package com.GrupoProga3.Gestor_Salud.HistoriaClinica;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.MAPPER.HistoriaClinicaMapper;
import com.GrupoProga3.Gestor_Salud.common.HistoriaClinicaNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.PacienteNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.common.UsuarioNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioHistoriaClinica implements IServicioHistoriaClinica {
    private final RepositorioHistoriaClinica repositorioHistoriaClinica;
    /// No sé si es correcto que se instancien estos repositorios de usuario y paciente
    /// en el servioio, preguntar al profe.
    private final RepositorioUsuario usuarioRepositorio;
    private final RepositorioPaciente repositorioPaciente;
    private final HistoriaClinicaMapper historiaClinicaMapper;

    public HistoriaClinicaRespuesta crear (HistoriaClinicaNueva historiaClinica){
        System.out.println(historiaClinica);
        EntidadHistoriaClinica historial= historiaClinicaMapper.toEntity(historiaClinica);

        /// Esto es para q se mapeen los ids a las entidades que hacen referencia correctamente
        EntidadUsuarios profesional = usuarioRepositorio
                .findById(historiaClinica.id_profesional())
                        .orElseThrow(()->new UsuarioNoEncontradoException("No se ha encontrado al profesional"));
        historial.setId_profesional(profesional);

        EntidadPaciente paciente = repositorioPaciente
                .findById(historiaClinica.id_paciente())
                .orElseThrow(()->new PacienteNoEncontradoException("No se ha encontrado al paciente"));
        historial.setId_paciente(paciente);

        EntidadHistoriaClinica guardado = repositorioHistoriaClinica.save(historial);
        System.out.println(guardado);
        return historiaClinicaMapper.toDTO(guardado);
    }

    public List<HistoriaClinicaRespuesta> buscarTodos(){
        return repositorioHistoriaClinica.findAll()
                .stream()
                .map(historiaClinicaMapper::toDTO)
                .toList();
    }

    public HistoriaClinicaRespuesta buscarPorId(Long id){
        EntidadHistoriaClinica buscado = repositorioHistoriaClinica.findById(id)
                .orElseThrow(()->new HistoriaClinicaNoEncontradaException("Historia Clinica no encontrada"));
        return historiaClinicaMapper.toDTO(buscado);
    }

    public HistoriaClinicaRespuesta actualizar (Long id, HistoriaClinicaActualizar historiaClinica){
        EntidadHistoriaClinica buscado = repositorioHistoriaClinica
                .findById(id)
                .orElseThrow(()-> new HistoriaClinicaNoEncontradaException("Historia Clinica no encontrada"));
        buscado.setEvolucion(historiaClinica.evolucion());
        buscado.setObservaciones(historiaClinica.observaciones());
        EntidadHistoriaClinica actualizado = repositorioHistoriaClinica.save(buscado);
        return historiaClinicaMapper.toDTO(actualizado);
    }

    public void borrar(Long id){
        EntidadHistoriaClinica buscado = repositorioHistoriaClinica
                .findById(id)
                .orElseThrow(()->new HistoriaClinicaNoEncontradaException("Historia Clinica no encontrada"));
        repositorioHistoriaClinica.delete(buscado);
    }

}
