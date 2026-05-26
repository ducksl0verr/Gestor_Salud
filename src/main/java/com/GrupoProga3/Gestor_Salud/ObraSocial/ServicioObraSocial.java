package com.GrupoProga3.Gestor_Salud.ObraSocial;

import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.Mappers.ObraSocialMapper;
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
    public ObraSocialDTO guardar(ObraSocialDTO obrasocialDTO) {
        var entidad = obraSocialMapper.toEntity(obrasocialDTO);
        var guardado = repositorioObraSocial.save(entidad);
        return obraSocialMapper.toDto(guardado);
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
    public ObraSocialDTO buscarPorId(Long id) {
        return repositorioObraSocial.findById(id)
                .map(obraSocialMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("ObraSocial no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public ObraSocialDTO actualizar(Long id, ObraSocialDTO obrasocialDTO) {
        EntidadObraSocial obra = repositorioObraSocial.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ObraSocial no encontrada con id: " + id));

        if (obrasocialDTO.getNombre() != null) obra.setNombre(obrasocialDTO.getNombre());
        if (obrasocialDTO.getCobertura() != null) obra.setCobertura(obrasocialDTO.getCobertura());

        EntidadObraSocial actualizado = repositorioObraSocial.save(obra);
        return obraSocialMapper.toDto(actualizado);
    }

    @Override
    public List<ObraSocialDTO> buscarTodos() {
        return repositorioObraSocial.findAll().stream()
                .map(obraSocialMapper::toDto)
                 .toList();
    }
}

