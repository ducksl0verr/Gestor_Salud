package com.GrupoProga3.Gestor_Salud.Usuarios;

import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.DTO.UsuarioDTO;
import com.GrupoProga3.Gestor_Salud.Usuarios.Dominio.Mappers.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioUsuario implements IServicioUsuario{

    private final RepositorioUsuario repositorioUsuario;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO guardar(UsuarioDTO usuarioDTO) {

        EntidadUsuarios guardado = repositorioUsuario.save(usuarioMapper.ToEntity(usuarioDTO));

        return usuarioMapper.ToDto(guardado);
    }

    @Override
    public void borrar(Long id) {
        this.repositorioUsuario.findById(id).ifPresent(repositorioUsuario::delete);

    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        return repositorioUsuario.findById(id)
                .map(usuarioMapper::ToDto)
                .orElseThrow();
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO) {

        EntidadUsuarios usu = repositorioUsuario.findById(id)
                .orElseThrow();
        usu.setNombre(usuarioDTO.nombre());
        usu.setApellido(usuarioDTO.apellido());
        usu.setDni(usuarioDTO.dni());
        usu.setEmail(usuarioDTO.email());
        usu.setTelefono(usuarioDTO.telefono());
        usu.setMatricula(usuarioDTO.matricula());

        EntidadUsuarios actualizado = repositorioUsuario.save(usu);

        return usuarioMapper.ToDto(usu);
    }

    @Override
    public List<UsuarioDTO> buscarTodos() {
        return repositorioUsuario.findAll().stream().map(usuarioMapper::ToDto).toList();
    }
}
