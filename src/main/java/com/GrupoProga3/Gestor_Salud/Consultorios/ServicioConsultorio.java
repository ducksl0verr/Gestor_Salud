package com.GrupoProga3.Gestor_Salud.Consultorios;

import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioActualizar;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.DTOs.ConsultorioRespuesta;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.Consultorios.Dominio.MAPPER.ConsultorioMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Consultorios.ConsultorioNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioConsultorio implements  IServicioConsultorio{
    private final RepositorioConsultorio repositorioConsultorio;
    private final ConsultorioMapper consultorioMapper;

    @Override
    public ConsultorioRespuesta crear(ConsultorioNuevo consultorioNuevo) {
        System.out.println(consultorioNuevo);
        EntidadConsultorio consultorio = repositorioConsultorio.save(consultorioMapper.toEntity(consultorioNuevo));
        System.out.println(consultorio);
        return consultorioMapper.toDTO(consultorio);
    }

    @Override
    public ConsultorioRespuesta buscarPorId(Long id) {
        EntidadConsultorio buscado = repositorioConsultorio.findById(id)
                .orElseThrow(()-> new ConsultorioNoEncontradoException("Consultorio no encontrado"));
        return consultorioMapper.toDTO(buscado);
    }

    @Override
    public List<ConsultorioRespuesta> buscarTodos() {
        return repositorioConsultorio
                .findAll()
                .stream()
                .map(consultorioMapper::toDTO)
                .toList();
    }

    @Override
    public ConsultorioRespuesta actualizar(Long id, ConsultorioActualizar consultorioActualizar) {
        EntidadConsultorio buscado = repositorioConsultorio.findById(id)
                .orElseThrow(()-> new ConsultorioNoEncontradoException("Consultorio no encontrado"));

        buscado.setNombre(consultorioActualizar.nombre());

        EntidadConsultorio actualizado = repositorioConsultorio.save(buscado);

        return consultorioMapper.toDTO(actualizado);
    }

    @Override
    public void borrar(Long id) {
        EntidadConsultorio buscado = repositorioConsultorio.findById(id)
                .orElseThrow(()-> new ConsultorioNoEncontradoException("Consultorio no encontrado"));
        repositorioConsultorio.delete(buscado);
    }
}
