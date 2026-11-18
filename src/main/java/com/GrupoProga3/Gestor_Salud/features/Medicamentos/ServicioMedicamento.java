package com.GrupoProga3.Gestor_Salud.features.Medicamentos;

import com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.DTOs.MedicamentoActualizar;
import com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.DTOs.MedicamentoNuevo;
import com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.DTOs.MedicamentoRespuesta;
import com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.EntidadMedicamento;
import com.GrupoProga3.Gestor_Salud.features.Medicamentos.Dominio.MAPPER.MedicamentoMapper;
import com.GrupoProga3.Gestor_Salud.common.excepciones.EntidadNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.RecursoOcupadoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.ReglaNegocioException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMedicamento implements IServicioMedicamento {

    private final RepositorioMedicamento repositorioMedicamento;
    private final MedicamentoMapper  medicamentoMapper;

    @Override
    @Transactional
    public MedicamentoRespuesta crear(MedicamentoNuevo medicamentoNuevo) {
        validarFechaVencimiento(medicamentoNuevo.fechaVencimiento());
        validarDuplicado(
                medicamentoNuevo.nombre(),
                medicamentoNuevo.laboratorio(),
                medicamentoNuevo.descripcion(),
                null
        );

        EntidadMedicamento nuevo =
                repositorioMedicamento.save(medicamentoMapper.toEntity(medicamentoNuevo));

        return medicamentoMapper.toDTO(nuevo);
    }

    @Override
    public MedicamentoRespuesta buscarPorId(Long id) {

        EntidadMedicamento buscado = repositorioMedicamento
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Medicamento",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún medicamento con aquel ID."
                ));

        return medicamentoMapper.toDTO(buscado);
    }

    @Override
    public MedicamentoRespuesta buscarPorNombre (String nombre) {

        EntidadMedicamento buscado = repositorioMedicamento
                .findAll()
                .stream()
                .filter(m->estaActivo(m) && m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Medicamento",
                        "No se ha encontrado.",
                        1l,
                        "No se ha encontrado a ningún medicamento con aquel nombre."
                ));

        return medicamentoMapper.toDTO(buscado);
    }

    @Override
    public List<MedicamentoRespuesta> buscarTodos() {
        return repositorioMedicamento
                .findAll()
                .stream()
                .filter(this::estaActivo)
                .map(medicamentoMapper::toDTO)
                .toList();
    }

    @Override
    public List<MedicamentoRespuesta> buscarPorPrincipioActivo(String principioActivo) {
        return repositorioMedicamento
                .findAll()
                .stream()
                .filter( m->estaActivo(m) && m.getPrincipioActivo().equalsIgnoreCase(principioActivo))
                .map(medicamentoMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public MedicamentoRespuesta actualizar(Long id, MedicamentoActualizar medicamentoActualizar) {
        EntidadMedicamento buscado = repositorioMedicamento
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Medicamento",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún medicamento con aquel ID."
                ));

        validarDuplicado(
                medicamentoActualizar.nombre(),
                medicamentoActualizar.laboratorio(),
                medicamentoActualizar.descripcion(),
                id
        );

        buscado.setNombre(medicamentoActualizar.nombre());
        buscado.setPrincipioActivo(medicamentoActualizar.principioActivo());
        buscado.setLaboratorio(medicamentoActualizar.laboratorio());
        buscado.setDescripcion(medicamentoActualizar.descripcion());
        buscado.setStock(medicamentoActualizar.stock());
        buscado.setPrecio(medicamentoActualizar.precio());

        EntidadMedicamento actualizado = repositorioMedicamento.save(buscado);

        return medicamentoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public MedicamentoRespuesta darDeBaja(Long id) {
        EntidadMedicamento medicamento = repositorioMedicamento
                .findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "Medicamento",
                        "No se ha encontrado.",
                        id,
                        "No se ha encontrado a ningún medicamento con aquel ID."
                ));

        // Baja lógica: el registro permanece en la BD, pero sin stock y fuera de los listados.
        medicamento.setStock(0);

        return medicamentoMapper.toDTO(repositorioMedicamento.save(medicamento));
    }

    private void validarFechaVencimiento(LocalDate fechaVencimiento) {
        if (fechaVencimiento == null || !fechaVencimiento.isAfter(LocalDate.now())) {
            throw new ReglaNegocioException(
                    "La fecha de vencimiento debe ser posterior al día de hoy."
            );
        }
    }

    private void validarDuplicado(String nombre,
                                  String laboratorio,
                                  String descripcion,
                                  Long idExcluido) {
        boolean duplicado = repositorioMedicamento.findAll().stream()
                .filter(m -> idExcluido == null || !idExcluido.equals(m.getId()))
                .anyMatch(m -> normalizar(m.getNombre()).equalsIgnoreCase(normalizar(nombre))
                        && normalizar(m.getLaboratorio()).equalsIgnoreCase(normalizar(laboratorio))
                        && normalizar(m.getDescripcion()).equalsIgnoreCase(normalizar(descripcion)));

        if (duplicado) {
            throw new RecursoOcupadoException(
                    "Ya existe un medicamento con ese nombre, laboratorio y descripción."
            );
        }
    }

    private String normalizar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private boolean estaActivo(EntidadMedicamento medicamento) {
        return medicamento.getStock() != null && medicamento.getStock() > 0;
    }

    @Transactional
    public void eliminarVencidos (){
        repositorioMedicamento.deleteVencidos(LocalDate.now());
        System.out.println("Medicamentos vencidos eliminados.");
    }

}
