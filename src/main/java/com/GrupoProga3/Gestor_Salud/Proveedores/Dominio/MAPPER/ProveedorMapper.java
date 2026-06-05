package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorNuevo;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorRespuesta;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.EntidadProveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    EntidadProveedor toEntity (ProveedorNuevo nuevo);
    ProveedorRespuesta toDTO (EntidadProveedor entidad);

    /// Pongo este método para poder mapepar la lista de domicilios cuando creamos un nuevo proveedor
    EntidadDomicilio toEntity (DomicilioNuevo nuevo);

    /// Pongo este método para poder mappear la lista de domicilios del proveedor respuesta
    DomicilioRespuesta toDTO (EntidadDomicilio entidad);
}
