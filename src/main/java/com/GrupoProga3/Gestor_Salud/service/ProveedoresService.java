package com.GrupoProga3.Gestor_Salud.service;

import com.GrupoProga3.Gestor_Salud.entity.Proveedores.Proveedores;
import com.GrupoProga3.Gestor_Salud.entity.Proveedores.ProveedoresDTO;
import com.GrupoProga3.Gestor_Salud.entity.TipoProvedor.TipoProvedor;
import com.GrupoProga3.Gestor_Salud.exception.ResourceNotFoundException;
import com.GrupoProga3.Gestor_Salud.repository.ProveedoresRepository;
import com.GrupoProga3.Gestor_Salud.repository.TipoProvedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProveedoresService {

    private final ProveedoresRepository proveedoresRepository;
    private final TipoProvedorRepository tipoProvedorRepository;

    public ProveedoresService(ProveedoresRepository proveedoresRepository,
                              TipoProvedorRepository tipoProvedorRepository) {
        this.proveedoresRepository = proveedoresRepository;
        this.tipoProvedorRepository = tipoProvedorRepository;
    }

    @Transactional(readOnly = true)
    public List<ProveedoresDTO> getAll() {
        return proveedoresRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProveedoresDTO getById(Long id) {
        return proveedoresRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + id));
    }

    public ProveedoresDTO create(ProveedoresDTO dto) {
        Proveedores proveedor = new Proveedores();
        mapDtoToEntity(dto, proveedor);
        return toDTO(proveedoresRepository.save(proveedor));
    }

    public ProveedoresDTO update(Long id, ProveedoresDTO dto) {
        Proveedores proveedor = proveedoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + id));
        mapDtoToEntity(dto, proveedor);
        return toDTO(proveedoresRepository.save(proveedor));
    }

    public void delete(Long id) {
        if (!proveedoresRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado con id: " + id);
        }
        proveedoresRepository.deleteById(id);
    }

    private void mapDtoToEntity(ProveedoresDTO dto, Proveedores proveedor) {
        proveedor.setNombre(dto.getNombre());
        proveedor.setEmail(dto.getEmail());
        proveedor.setCuil(dto.getCuil());
        proveedor.setDireccion(dto.getDireccion());
        TipoProvedor tipoProvedor = tipoProvedorRepository.findById(dto.getIdTipoProvedor())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de proveedor no encontrado con id: " + dto.getIdTipoProvedor()));
        proveedor.setTipoProvedor(tipoProvedor);
    }

    private ProveedoresDTO toDTO(Proveedores proveedor) {
        ProveedoresDTO dto = new ProveedoresDTO();
        dto.setIdProvedor(proveedor.getIdProvedor());
        dto.setNombre(proveedor.getNombre());
        dto.setEmail(proveedor.getEmail());
        dto.setCuil(proveedor.getCuil());
        dto.setDireccion(proveedor.getDireccion());
        dto.setIdTipoProvedor(proveedor.getTipoProvedor().getIdTipoProvedor());
        return dto;
    }
}
