package com.GrupoProga3.Gestor_Salud.Quirofanos;

import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoActualizar;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoNuevo;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.DTOs.QuirofanoRespuesta;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.EntidadQuirofano;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.MAPPER.QuirofanoMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioQuirofano implements IServicioQuirofano {

    private final RepositorioQuirofano repositorioQuirofano;
    private final QuirofanoMapper  quirofanoMapper;

    @Override
    public QuirofanoRespuesta crear(QuirofanoNuevo quirofanoNuevo) {
        System.out.println(quirofanoNuevo);
        EntidadQuirofano nuevo = quirofanoMapper.toEntity(quirofanoNuevo);
        nuevo.setDisponible(true);
        EntidadQuirofano guardado = repositorioQuirofano.save(nuevo);
        System.out.println(guardado);
        return quirofanoMapper.toDTO(guardado);
    }

    @Override
    public QuirofanoRespuesta actualizar(Long id, QuirofanoActualizar quirofanoActualizar) {
        EntidadQuirofano buscado = repositorioQuirofano
                .findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException("Quirofano",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ninguna quirofano con aquel ID."));
        buscado.setDisponible(quirofanoActualizar.disponible());
        buscado.setNombre(quirofanoActualizar.nombre());
        EntidadQuirofano guardado = repositorioQuirofano.save(buscado);
        return quirofanoMapper.toDTO(guardado);
    }

    @Override
    public QuirofanoRespuesta buscarPorId(Long id) {
        EntidadQuirofano buscado = repositorioQuirofano
                .findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException("Quirofano",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ninguna quirofano con aquel ID."));
        return quirofanoMapper.toDTO(buscado);
    }

    @Override
    public List<QuirofanoRespuesta> buscarTodos() {
        return repositorioQuirofano
                .findAll()
                .stream()
                .map(quirofanoMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public QuirofanoRespuesta ocuparQuirofano(Long id) {
        EntidadQuirofano buscado = repositorioQuirofano
                .findById(id)
                .orElseThrow(()->new EntidadNoEncontradaException("Quirofano",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado ninguna quirofano con aquel ID."));

        buscado.setDisponible(false);
        EntidadQuirofano ocupado=repositorioQuirofano.save(buscado);
        return quirofanoMapper.toDTO(ocupado);
    }

}
