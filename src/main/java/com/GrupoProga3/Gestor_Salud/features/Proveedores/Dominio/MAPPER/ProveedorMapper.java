package com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Contacto.Model.EntidadContacto;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioNuevo;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.EntidadDomicilio;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorNuevo;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs.ProveedorRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.EntidadProveedor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    EntidadProveedor toEntity (ProveedorNuevo nuevo);
    ProveedorRespuesta toDTO (EntidadProveedor entidad);

    /// Pongo este método para poder mapepar la lista de domicilios cuando creamos un nuevo proveedor
    EntidadDomicilio toEntity (DomicilioNuevo nuevo);

    /// Pongo este método para poder mappear la lista de domicilios del proveedor respuesta
    DomicilioRespuesta toDTO (EntidadDomicilio entidad);

    EntidadContacto toEntity (ContactoNuevo nuevo);
}
