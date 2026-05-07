package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.Roles.Roles;
import com.GrupoProga3.Gestor_Salud.entity.Roles.RolesDTO;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.RolesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Transactional(readOnly = true)
    public List<RolesDTO> getAll() {
        return rolesRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public RolesDTO getById(Long id) {
        return rolesRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + id));
    }

    public RolesDTO create(RolesDTO dto) {
        Roles rol = new Roles();
        rol.setNombre(dto.getNombre());
        return toDTO(rolesRepository.save(rol));
    }

    public RolesDTO update(Long id, RolesDTO dto) {
        Roles rol = rolesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + id));
        rol.setNombre(dto.getNombre());
        return toDTO(rolesRepository.save(rol));
    }

    public void delete(Long id) {
        if (!rolesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rol no encontrado con id: " + id);
        }
        rolesRepository.deleteById(id);
    }

    private RolesDTO toDTO(Roles rol) {
        RolesDTO dto = new RolesDTO();
        dto.setIdRol(rol.getIdRol());
        dto.setNombre(rol.getNombre());
        return dto;
    }
}
