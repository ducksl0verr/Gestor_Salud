package com.GrupoProga3.Gestor_Salud.Contacto.Servicio;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoDTO;
import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.Mappers.ContactoMapper;
import com.GrupoProga3.Gestor_Salud.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.Contacto.Repositorio.RepositorioContacto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServicioContacto implements IServicioContacto {

    private final RepositorioContacto repositorioContacto;
    private final ContactoMapper contactoMapper;

    @Override
    public ContactoDTO guardar(ContactoDTO contactoDTO) {
        EntidadContacto guardado = repositorioContacto.save(contactoMapper.toEntity(contactoDTO));
        System.out.println(guardado);
        return contactoMapper.toDto(guardado);
    }

    @Override
    public void borrar(Long id) {
        this.repositorioContacto.findById(id).ifPresent(repositorioContacto::delete);
    }

    @Override
    public ContactoDTO buscarPorId(Long id) {
        return repositorioContacto.findById(id)
                .map(contactoMapper::toDto)
                .orElseThrow(); //acá deberíamos agregar una excepción personalizada, recordar hacerlas
    }

    @Override
    public ContactoDTO actualizar(Long id, ContactoDTO contactoDTO) {
        EntidadContacto cont = repositorioContacto.findById(id)
                .orElseThrow();
        cont.setNombre(contactoDTO.nombre());
        cont.setApellido(contactoDTO.apellido());
        cont.setTelefono(contactoDTO.telefono());
        EntidadContacto actualizado = repositorioContacto.save(cont);
        return contactoMapper.toDto(actualizado);

    }

    @Override
    public List<ContactoDTO> buscarTodos() {
        return repositorioContacto.findAll().stream().map(contactoMapper::toDto).toList();
    }
}

