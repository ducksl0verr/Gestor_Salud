package com.GrupoProga3.Gestor_Salud.Medicamentos;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoActualizar;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoNuevo;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoRespuesta;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.EntidadMedicamento;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.MAPPER.MedicamentoMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Medicamentos.MedicamentoNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMedicamento implements IServicioMedicamento {

    private final RepositorioMedicamento repositorioMedicamento;
    private final MedicamentoMapper  medicamentoMapper;

    @Override
    public MedicamentoRespuesta crear(MedicamentoNuevo medicamentoNuevo) {
        System.out.println(medicamentoNuevo);

        EntidadMedicamento nuevo = repositorioMedicamento.save(medicamentoMapper.toEntity(medicamentoNuevo));

        System.out.println(nuevo);

        return medicamentoMapper.toDTO(nuevo);
    }

    @Override
    public MedicamentoRespuesta buscarPorId(Long id) {

        EntidadMedicamento buscado = repositorioMedicamento
                .findById(id)
                .orElseThrow(() -> new MedicamentoNoEncontradoException("Medicamento no encontrado"));

        return medicamentoMapper.toDTO(buscado);
    }

    @Override
    public MedicamentoRespuesta buscarPorNombre (String nombre) {

        EntidadMedicamento buscado = repositorioMedicamento
                .findAll()
                .stream()
                .filter(m->m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new MedicamentoNoEncontradoException("Medicamento no encontrado"));

        return medicamentoMapper.toDTO(buscado);
    }

    @Override
    public List<MedicamentoRespuesta> buscarTodos() {
        return repositorioMedicamento
                .findAll()
                .stream()
                .map(medicamentoMapper::toDTO)
                .toList();
    }

    @Override
    public List<MedicamentoRespuesta> buscarPorPrincipioActivo(String principioActivo) {
        return repositorioMedicamento
                .findAll()
                .stream()
                .filter( m->m.getPrincipioActivo().equalsIgnoreCase(principioActivo))
                .map(medicamentoMapper::toDTO)
                .toList();
    }

    @Override
    public MedicamentoRespuesta actualizar(Long id, MedicamentoActualizar medicamentoActualizar) {
        EntidadMedicamento buscado = repositorioMedicamento
                .findById(id)
                .orElseThrow(() -> new MedicamentoNoEncontradoException("Medicamento no encontrado"));

        buscado.setNombre(medicamentoActualizar.nombre());
        buscado.setPrincipioActivo(medicamentoActualizar.principioActivo());
        buscado.setLaboratorio(medicamentoActualizar.laboratorio());
        buscado.setDescripcion(medicamentoActualizar.descripcion());
        buscado.setStock(medicamentoActualizar.stock());
        buscado.setPrecio(medicamentoActualizar.precio());

        EntidadMedicamento actualizado = repositorioMedicamento.save(buscado);

        return medicamentoMapper.toDTO(actualizado);
    }

}
