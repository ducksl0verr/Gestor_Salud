package com.GrupoProga3.Gestor_Salud.features.Consultorios;

import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioActualizar;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.DTOs.ConsultorioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.EntidadConsultorio;
import com.GrupoProga3.Gestor_Salud.features.Consultorios.Dominio.MAPPER.ConsultorioMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioConsultorio implements  IServicioConsultorio{
    private final RepositorioConsultorio repositorioConsultorio;
    private final ConsultorioMapper consultorioMapper;

    @Override
    @Transactional
    public ConsultorioRespuesta crear(ConsultorioNuevo consultorioNuevo) {
        System.out.println(consultorioNuevo);
        EntidadConsultorio consultorio = repositorioConsultorio.save(consultorioMapper.toEntity(consultorioNuevo));
        System.out.println(consultorio);
        return consultorioMapper.toDTO(consultorio);
    }

    @Override
    public ConsultorioRespuesta buscarPorId(Long id) {
        EntidadConsultorio buscado = repositorioConsultorio.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Consultorio",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún consultorio con aquel ID."
                ));
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
    @Transactional
    public ConsultorioRespuesta actualizar(Long id, ConsultorioActualizar consultorioActualizar) {
        EntidadConsultorio buscado = repositorioConsultorio.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Consultorio",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún consultorio con aquel ID."
                ));

        buscado.setNombre(consultorioActualizar.nombre());

        EntidadConsultorio actualizado = repositorioConsultorio.save(buscado);

        return consultorioMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadConsultorio buscado = repositorioConsultorio.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Consultorio",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún consultorio con aquel ID."
                ));
        repositorioConsultorio.delete(buscado);
    }
}
