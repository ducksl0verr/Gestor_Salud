package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Contacto.Dominio.DTO.ContactoRespuesta;
import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.Proveedores.Enums.TIPO_PROVEEDOR;
import java.util.List;

public record ProveedorRespuesta(Long id,
                                 String nombre,
                                 ContactoRespuesta contacto,
                                 String cuil,
                                 List<DomicilioRespuesta> direccion,
                                 TIPO_PROVEEDOR tipo) {
}
