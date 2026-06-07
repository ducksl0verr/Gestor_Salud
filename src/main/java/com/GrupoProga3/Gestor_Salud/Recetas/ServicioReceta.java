package com.GrupoProga3.Gestor_Salud.Recetas;

import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.DTOs.DetalleRecetaNuevo;
import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.EntidadDetalleReceta;
import com.GrupoProga3.Gestor_Salud.DetallesRecetas.Dominio.MAPPER.DetalleRecetaMapper;
import com.GrupoProga3.Gestor_Salud.DetallesRecetas.RepositorioDetalleReceta;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.EntidadMedicamento;
import com.GrupoProga3.Gestor_Salud.Medicamentos.RepositorioMedicamento;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs.RecetaNueva;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.DTOs.RecetaRespuesta;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.EntidadReceta;
import com.GrupoProga3.Gestor_Salud.Recetas.Dominio.MAPPER.RecetaMapper;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.PacienteNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.common.UsuarioNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Medicamentos.MedicamentoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioReceta implements IServicioReceta {

    private final RepositorioReceta repositorioReceta;
    private final RepositorioPaciente  repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioDetalleReceta repositorioDetalleReceta;
    private final RepositorioMedicamento repositorioMedicamento;
    private final DetalleRecetaMapper detalleRecetaMapper;
    private final RecetaMapper recetaMapper;

    @Override
    public RecetaRespuesta crear(RecetaNueva receta) {
        System.out.println(receta);
        EntidadReceta nueva = recetaMapper.toEntity(receta);

        nueva.setFecha(LocalDate.now());

        EntidadPaciente paciente = repositorioPaciente
                .findById(receta.id_paciente())
                .orElseThrow(()-> new PacienteNoEncontradoException("No se encontro el paciente"));
        nueva.setPaciente(paciente);

        EntidadUsuarios profesional = repositorioUsuario
                .findById(receta.id_profesional())
                .orElseThrow(()-> new UsuarioNoEncontradoException("No es encontro al profesional"));
        nueva.setProfesional(profesional);

        List<EntidadDetalleReceta> detalles = new ArrayList<>();

        for (DetalleRecetaNuevo detallesdto : receta.detalles()){
            EntidadMedicamento medicamento =
                    repositorioMedicamento.findById(detallesdto.id_medicamento())
                            .orElseThrow(()->new MedicamentoNoEncontradoException("Medicamento no encontrado"));

            if (medicamento.getStock()<detallesdto.cantidad()){
                throw  new RuntimeException("Stock insuficiente para "+ medicamento.getNombre());
            }

            medicamento.descontarStock(detallesdto.cantidad());

            EntidadDetalleReceta detalle = detalleRecetaMapper.toEntity(detallesdto);

            detalle.setMedicamento(medicamento);
            detalle.setReceta(nueva);

            detalles.add(detalle);
        }

        nueva.setDetalles(detalles);

        EntidadReceta guardado = repositorioReceta
                .save(nueva);

        return recetaMapper.toDTO(guardado);
    }

    @Override
    public RecetaRespuesta buscarPorId(Long id) {
        EntidadReceta buscada = repositorioReceta
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException("No se ha encontrado la receta"));

        return recetaMapper.toDTO(buscada);
    }

    @Override
    public List<RecetaRespuesta> buscarTodos() {
        return repositorioReceta
                .findAll()
                .stream()
                .map(recetaMapper::toDTO)
                .toList();
    }

    @Override
    public List<RecetaRespuesta> buscarPorPaciente(Long id_paciente) {
        return repositorioReceta
                .findAll()
                .stream()
                .filter(p->p.getPaciente().getId()==id_paciente)
                .map(recetaMapper::toDTO)
                .toList();
    }
}
