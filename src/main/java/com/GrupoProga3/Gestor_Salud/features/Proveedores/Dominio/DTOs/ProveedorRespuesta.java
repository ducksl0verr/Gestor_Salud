package com.GrupoProga3.Gestor_Salud.features.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.features.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Proveedores.Enums.TIPO_PROVEEDOR;
import java.util.List;

public record ProveedorRespuesta(Long id,
                                 String nombre,
                                 ContactoRespuesta contacto,
                                 String cuil,
                                 List<DomicilioRespuesta> direccion,
                                 TIPO_PROVEEDOR tipo) {
}
