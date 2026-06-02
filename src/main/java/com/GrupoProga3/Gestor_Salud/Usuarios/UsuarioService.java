package com.GrupoProga3.Gestor_Salud.Usuarios;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioDTO usuarioDTO;


}
