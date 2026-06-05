package com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs;

import com.GrupoProga3.Gestor_Salud.Domicilio.Dominio.DTO.DomicilioRespuesta;
import com.GrupoProga3.Gestor_Salud.Proveedores.Enums.TIPO_PROVEEDOR;
import java.util.List;

public record ProveedorRespuesta(Long id,
                                 String nombre,
                                 String email,
                                 String cuil,
                                 List<DomicilioRespuesta> direccion,
                                 TIPO_PROVEEDOR tipo) {
}
