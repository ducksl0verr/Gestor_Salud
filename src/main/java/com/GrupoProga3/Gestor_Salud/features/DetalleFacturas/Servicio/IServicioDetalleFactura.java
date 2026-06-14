package com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Servicio;
import com.GrupoProga3.Gestor_Salud.features.DetalleFacturas.Dominio.DTO.DetalleFacturaDTO;
import java.util.List;

public interface IServicioDetalleFactura {
    DetalleFacturaDTO guardar(DetalleFacturaDTO detalleFacturaDTO);
    void borrar (Long id);
    DetalleFacturaDTO actualizar(Long id,DetalleFacturaDTO detalleFacturaDTO);
    DetalleFacturaDTO buscarPorId(Long id);
    List<DetalleFacturaDTO> buscarTodos();
}
