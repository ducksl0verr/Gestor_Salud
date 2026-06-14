package com.GrupoProga3.Gestor_Salud.ObraSocial;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialDTO;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialNueva;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.DTO.ObraSocialRespuesta;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Dominio.MAPPER.ObraSocialMapper;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoExistenteException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.RecursoNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.ObraSocial.Excepciones.ReglaNegocioException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)

public class ServicioObraSocial implements IServicioObraSocial {

    private final RepositorioObraSocial repositorioObraSocial;
    private final ObraSocialMapper obraSocialMapper;


    @Override
    @Transactional

    public ObraSocialRespuesta guardar(ObraSocialNueva obraSocialNueva) {

        if (repositorioObraSocial.existsByNombre(obraSocialNueva.nombre())) {
            throw new RecursoExistenteException(
                    "Ya existe una obra social con nombre: "
                            + obraSocialNueva.nombre()
            );
        }

        if (obraSocialNueva.domicilios() == null
                || obraSocialNueva.domicilios().isEmpty()) {
            throw new ReglaNegocioException(
                    "La obra social debe tener al menos un domicilio."
            );
        }

        EntidadObraSocial obraSocial =
                obraSocialMapper.toEntity(obraSocialNueva);

        EntidadObraSocial guardada =
                repositorioObraSocial.save(obraSocial);

        return obraSocialMapper.toDTO(guardada);
    }

    @Override
    @Transactional
    public ObraSocialRespuesta actualizar(Long id,
                                          ObraSocialDTO obraSocialDTO) {

        EntidadObraSocial obraSocial =
                repositorioObraSocial.findById(id)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "La obra social con id: "
                                                + id
                                                + " no existe."
                                ));

        if (obraSocialDTO.getNombre() != null
                && !obraSocialDTO.getNombre().isBlank()) {

            if (!obraSocial.getNombre().equalsIgnoreCase(obraSocialDTO.getNombre())
                    && repositorioObraSocial.existsByNombre(obraSocialDTO.getNombre())) {

                throw new ReglaNegocioException(
                        "Ya existe una obra social con nombre: "
                                + obraSocialDTO.getNombre()
                );
            }

            obraSocial.setNombre(obraSocialDTO.getNombre());
        }

        if (obraSocialDTO.getCobertura() != null
                && !obraSocialDTO.getCobertura().isBlank()) {

            obraSocial.setCobertura(obraSocialDTO.getCobertura());
        }

        EntidadObraSocial actualizada =
                repositorioObraSocial.save(obraSocial);

        return obraSocialMapper.toDTO(actualizada);
    }

    @Override
    @Transactional
    public void borrar(Long id) {

        EntidadObraSocial obraSocial =
                repositorioObraSocial.findById(id)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "La obra social con id: "
                                                + id
                                                + " no existe."
                                ));

        if (obraSocial.getDomicilios() != null
                && !obraSocial.getDomicilios().isEmpty()) {

            throw new ReglaNegocioException(
                    "No se puede eliminar una obra social que posee domicilios asociados."
            );
        }

        repositorioObraSocial.delete(obraSocial);
    }


    public List<ObraSocialRespuesta> buscarObraSocial(
            String nombre,
            String cobertura) {

        if (nombre != null && cobertura != null) {
            return repositorioObraSocial
                    .findByNombreContainingAndCoberturaContaining(
                            nombre,
                            cobertura)
                    .stream()
                    .map(obraSocialMapper::toDTO)
                    .toList();
        }

        if (nombre != null) {
            return repositorioObraSocial
                    .findByNombreContaining(nombre)
                    .stream()
                    .map(obraSocialMapper::toDTO)
                    .toList();
        }

        if (cobertura != null) {
            return repositorioObraSocial
                    .findByCoberturaContaining(cobertura)
                    .stream()
                    .map(obraSocialMapper::toDTO)
                    .toList();
        }

        return repositorioObraSocial.findAll()
                .stream()
                .map(obraSocialMapper::toDTO)
                .toList();

    }

    public List<DomicilioNuevo> buscarDomiciliosPorObraSocial(Long idObraSocial) {

        EntidadObraSocial obraSocial =
                repositorioObraSocial.findById(idObraSocial)
                        .orElseThrow(() ->
                                new RecursoNoEncontradoException(
                                        "La obra social con id: "
                                                + idObraSocial
                                                + " no existe."
                                ));

        return obraSocial.getDomicilios()
                .stream()
                .map(obraSocialMapper::toDTO)
                .toList();
    }
    @Override
    @Transactional
    public List<ObraSocialRespuesta> buscarTodos() {
        return repositorioObraSocial.findAll()
                .stream()
                .map(obraSocialMapper::toDTO)
                .toList();
    }

    @Override
    public ObraSocialRespuesta buscarPorId(Long id) {
        EntidadObraSocial obra = repositorioObraSocial
                .findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException(
                        "Obra Social",
                        "No se ha encontrado",
                        id,
                        "No se ha encontrado ninguna obra social con aquel ID."
                ));

        return obraSocialMapper.toDTO(obra);
    }
}