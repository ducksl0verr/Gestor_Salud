package com.GrupoProga3.Gestor_Salud.DetalleFacturas.Servicio;

import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Excepciones.DetalleFacturaNoEncontrado;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Dominio.DTO.DetalleFacturaDTO;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Dominio.Mappers.DetalleFacturaMapper;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Model.EntidadDetalleFacturas;
import com.GrupoProga3.Gestor_Salud.DetalleFacturas.Repositorio.RepositorioDetalleFactura;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDetalleFactura implements IServicioDetalleFactura{

    private final RepositorioDetalleFactura repositorioDetalleFactura;
    private final DetalleFacturaMapper detalleFacturaMapper;

    @Override
    @Transactional
    public DetalleFacturaDTO guardar(DetalleFacturaDTO detalleFacturaDTO) {
        EntidadDetalleFacturas guardado = repositorioDetalleFactura.save(detalleFacturaMapper.toEntity(detalleFacturaDTO));
        return detalleFacturaMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public void borrar(Long id) {
        EntidadDetalleFacturas df = repositorioDetalleFactura.findById(id)
                .orElseThrow(DetalleFacturaNoEncontrado::new);

        repositorioDetalleFactura.delete(df);
    }

    @Override
    @Transactional
    public DetalleFacturaDTO actualizar(Long id, DetalleFacturaDTO detalleFacturaDTO) {
        EntidadDetalleFacturas detalleFacturas = repositorioDetalleFactura.findById(id)
                .orElseThrow(DetalleFacturaNoEncontrado::new);

        detalleFacturas.setConcepto(detalleFacturaDTO.concepto());
        detalleFacturas.setImporte(detalleFacturaDTO.importe());
        detalleFacturas.setCantidad(detalleFacturaDTO.cantidad());

        EntidadDetalleFacturas actualizado = repositorioDetalleFactura.save(detalleFacturas);

        return detalleFacturaMapper.toDTO(actualizado);
    }

    @Override
    public DetalleFacturaDTO buscarPorId(Long id) {
        return repositorioDetalleFactura.findById(id).map(detalleFacturaMapper::toDTO).orElseThrow(DetalleFacturaNoEncontrado::new);
    }

    @Override
    public List<DetalleFacturaDTO> buscarTodos() {
        return repositorioDetalleFactura.findAll().stream().map(detalleFacturaMapper::toDTO).toList();
    }
}
