package com.GrupoProga3.Gestor_Salud.features.Domicilio;

import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.Mappers.DomicilioMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServicioDomicilio implements IServicioDomicilio{

    private final RepositorioDomicilio repositorioDomicilio;
    private final DomicilioMapper domicilioMapper;

    @Override
    @Transactional
    public DomicilioRespuesta guardar(DomicilioNuevo domicilioNuevo) {
        EntidadDomicilio guardado = repositorioDomicilio.save(domicilioMapper.toEntity(domicilioNuevo));
        System.out.println(guardado);
        return domicilioMapper.toDto(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        this.repositorioDomicilio.findById(id).ifPresent(repositorioDomicilio::delete);
    }

    @Override
    public DomicilioRespuesta buscarPorId(Long id) {
        return repositorioDomicilio.findById(id)
                .map(domicilioMapper::toDto)
                .orElseThrow(()-> new EntidadNoEncontradaException("Domicilio",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún domicilio con aquel ID."));
    }

    @Override
    @Transactional
    public DomicilioRespuesta actualizar(Long id, DomicilioNuevo domicilioNuevo) {
        EntidadDomicilio dom = repositorioDomicilio.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Domicilio",
                        "No encontrado",
                        id,
                        "No se ha encontrado ningún domicilio con aquel ID."));
        dom.setCalle(domicilioNuevo.calle());
        dom.setDepto(domicilioNuevo.depto());
        dom.setPiso(domicilioNuevo.piso());
        dom.setLocalidad(domicilioNuevo.localidad());
        dom.setCodigo_postal(domicilioNuevo.codigo_postal());
        dom.setProvincia(domicilioNuevo.provincia());
        EntidadDomicilio actualizado = repositorioDomicilio.save(dom);
        return domicilioMapper.toDto(actualizado);
    }

    @Override
    public List<DomicilioRespuesta> buscarTodos() {
        return repositorioDomicilio.findAll().stream().map(domicilioMapper::toDto).toList();
    }
}
