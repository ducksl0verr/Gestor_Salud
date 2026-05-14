package com.GrupoProga3.Gestor_Salud.Domicilio;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioDTO;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.Mappers.DomicilioMapper;
import com.GrupoProga3.Gestor_Salud.common.DomicilioNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServicioDomicilio implements IServicioDomicilio{

    private final RepositorioDomicilio repositorioDomicilio;
    private final DomicilioMapper domicilioMapper;

    @Override
    public DomicilioDTO guardar(DomicilioDTO domicilioDTO) {
        EntidadDomicilio guardado = repositorioDomicilio.save(domicilioMapper.toEntity(domicilioDTO));
        System.out.println(guardado);
        return domicilioMapper.toDto(guardado);
    }

    @Override
    public void borrar(Long id) {
        this.repositorioDomicilio.findById(id).ifPresent(repositorioDomicilio::delete);
    }

    @Override
    public DomicilioDTO buscarPorId(Long id) {
        return repositorioDomicilio.findById(id)
                .map(domicilioMapper::toDto)
                .orElseThrow(()-> new DomicilioNoEncontradoException("No se ha encontrado el domicilio."));
    }

    @Override
    public DomicilioDTO actualizar(Long id, DomicilioDTO domicilioDTO) {
        EntidadDomicilio dom = repositorioDomicilio.findById(id)
                .orElseThrow();
        dom.setCalle(domicilioDTO.calle());
        dom.setDepto(domicilioDTO.depto());
        dom.setPiso(domicilioDTO.piso());
        dom.setLocalidad(domicilioDTO.localidad());
        dom.setCodigo_postal(domicilioDTO.codigo_postal());
        dom.setProvincia(domicilioDTO.provincia());
        EntidadDomicilio actualizado = repositorioDomicilio.save(dom);
        return domicilioMapper.toDto(actualizado);
    }

    @Override
    public List<DomicilioDTO> buscarTodos() {
        return repositorioDomicilio.findAll().stream().map(domicilioMapper::toDto).toList();
    }
}
