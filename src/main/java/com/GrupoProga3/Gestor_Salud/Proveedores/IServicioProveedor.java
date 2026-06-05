package com.GrupoProga3.Gestor_Salud.Proveedores;

import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorActualizar;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorNuevo;
import com.GrupoProga3.Gestor_Salud.Proveedores.Dominio.DTOs.ProveedorRespuesta;

import java.util.List;

public interface IServicioProveedor {
    ProveedorRespuesta crear (ProveedorNuevo nuevo);
    ProveedorRespuesta buscarPorId (Long id);
    List<ProveedorRespuesta> buscarTodos();
    ProveedorRespuesta buscarPorNombre (String nombre);
    ProveedorRespuesta actualizar (Long id, ProveedorActualizar nuevo);
    void borrar (Long id);
}
