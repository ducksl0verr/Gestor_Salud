package com.GrupoProga3.Gestor_Salud.Roles;

import com.GrupoProga3.Gestor_Salud.Roles.Dominio.DTOs.RolActualizar;
import com.GrupoProga3.Gestor_Salud.Roles.Dominio.DTOs.RolRespuesta;
import com.GrupoProga3.Gestor_Salud.Roles.Dominio.EntidadRol;
import com.GrupoProga3.Gestor_Salud.Roles.Dominio.MAPPER.RolMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioRol implements IServicioRol {
    private final RepositorioRol repositorioRol;
    private final RolMapper rolMapper;
    @Override
    public RolRespuesta guardar(RolActualizar rolActualizar) {
        System.out.println(rolActualizar);
        EntidadRol guardado=repositorioRol.save(rolMapper.toEntity(rolActualizar));
        System.out.println(guardado);
        return rolMapper.toDTO(guardado);
    }

    @Override
    public RolRespuesta actualizar(Long id, RolActualizar rolActualizar) {
        EntidadRol buscado = repositorioRol
                .findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException(
                        "Profesional",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún profesional con aquel ID."
                ));
        buscado.setNombre(rolActualizar.nombre());
        EntidadRol actualizado=repositorioRol.save(buscado);
        return rolMapper.toDTO(actualizado);
    }

    @Override
    public RolRespuesta buscarPorId(Long id) {
        EntidadRol buscado = repositorioRol
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Profesional",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún profesional con aquel ID."
                ));
        return rolMapper.toDTO(buscado);
    }

    @Override
    public List<RolRespuesta> buscarTodos() {
        return repositorioRol
                .findAll()
                .stream()
                .map(rolMapper::toDTO)
                .toList();
    }
}
