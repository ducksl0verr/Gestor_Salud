package com.GrupoProga3.Gestor_Salud.ObraSocial;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.MAPPER.ObraSocialMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ServicioObraSocial implements IServicioObraSocial {
    private final RepositorioObraSocial repositorioObraSocial;
    private final ObraSocialMapper obraSocialMapper;

    @Override
    public ObraSocialRespuesta guardar(ObraSocialNueva obrasocialDTO) {
        var entidad = obraSocialMapper.toEntity(obrasocialDTO);
        var guardado = repositorioObraSocial.save(entidad);
        return obraSocialMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        if (!repositorioObraSocial.existsById(id)) {
            throw new NoSuchElementException("ObraSocial no encontrada con id: " + id);
        }
        repositorioObraSocial.deleteById(id);
    }

    @Override
    public ObraSocialRespuesta buscarPorId(Long id) {
        return repositorioObraSocial.findById(id)
                .map(obraSocialMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("ObraSocial no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public ObraSocialRespuesta actualizar(Long id, ObraSocialDTO obrasocialDTO) {
        EntidadObraSocial obra = repositorioObraSocial.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ObraSocial no encontrada con id: " + id));

        if (obrasocialDTO.getNombre() != null) obra.setNombre(obrasocialDTO.getNombre());
        if (obrasocialDTO.getCobertura() != null) obra.setCobertura(obrasocialDTO.getCobertura());

        EntidadObraSocial actualizado = repositorioObraSocial.save(obra);
        return obraSocialMapper.toDTO(actualizado);
    }

    @Override
    public List<ObraSocialRespuesta> buscarTodos() {
        return repositorioObraSocial.findAll().stream()
                .map(obraSocialMapper::toDTO)
                 .toList();
    }
}

