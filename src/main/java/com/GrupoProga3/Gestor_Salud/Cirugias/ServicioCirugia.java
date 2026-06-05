package com.GrupoProga3.Gestor_Salud.Cirugias;

import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs.CirugiaActualizar;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs.CirugiaNueva;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.DTOs.CirugiaRespuesta;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.EntidadCirugia;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.Enums.EstadoCirugia;
import com.GrupoProga3.Gestor_Salud.Cirugias.Dominio.MAPPER.CirugiaMapper;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Pacientes.Repositorio.RepositorioPaciente;
import com.GrupoProga3.Gestor_Salud.Quirofanos.Dominio.EntidadQuirofano;
import com.GrupoProga3.Gestor_Salud.Quirofanos.RepositorioQuirofano;
import com.GrupoProga3.Gestor_Salud.Usuarios.Model.EntidadUsuarios;
import com.GrupoProga3.Gestor_Salud.Usuarios.Repositorio.RepositorioUsuario;
import com.GrupoProga3.Gestor_Salud.common.PacienteNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.common.UsuarioNoEncontradoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Cirugias.CirugiaEnCursoException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Cirugias.CirugiaNoEncontradaException;
import com.GrupoProga3.Gestor_Salud.common.excepciones.Quirofanos.QuirofanoNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCirugia implements IServicioCirugia {
    private final RepositorioCirugia repositorioCirugia;
    private final RepositorioPaciente  repositorioPaciente;
    private final RepositorioQuirofano  repositorioQuirofano;;
    private final RepositorioUsuario repositorioUsuario;
    private final CirugiaMapper  cirugiaMapper;

    @Override
    public CirugiaRespuesta crear(CirugiaNueva cirugiaNueva) {
        System.out.println(cirugiaNueva);
        EntidadCirugia cirugia = cirugiaMapper.toEntity(cirugiaNueva);

        EntidadUsuarios cirujano = repositorioUsuario.findById(cirugiaNueva.idCirujano())
                .orElseThrow(()-> new UsuarioNoEncontradoException("No se encontró ningún cirujano con aquel ID"));

        cirugia.setCirujano(cirujano);

        EntidadPaciente paciente = repositorioPaciente.findById(cirugiaNueva.idPaciente())
                .orElseThrow(()-> new PacienteNoEncontradoException("No se encontró al paciente"));

        cirugia.setPaciente(paciente);

        EntidadQuirofano quirofano = repositorioQuirofano.findById(cirugiaNueva.idQuirofano())
                .orElseThrow(()-> new QuirofanoNoEncontradoException("No se encontró ningún quirofano con aquel ID"));

        cirugia.setQuirofano(quirofano);

        EntidadCirugia guardado =  repositorioCirugia.save(cirugia);

        System.out.println(guardado);

        return cirugiaMapper.toDTO(guardado);
    }

    @Override
    public CirugiaRespuesta buscarPorID(Long id) {
        EntidadCirugia buscada = repositorioCirugia.findById(id)
                .orElseThrow(()-> new CirugiaNoEncontradaException("No existe ninguna cirugia con aquel ID"));
        return cirugiaMapper.toDTO(buscada);
    }

    @Override
    public List<CirugiaRespuesta> buscarTodos() {
        return repositorioCirugia
                .findAll()
                .stream()
                .map(cirugiaMapper::toDTO)
                .toList();
    }

    @Override
    public CirugiaRespuesta actualizar(Long id, CirugiaActualizar cirugiaActualizar) {
        EntidadCirugia buscada = repositorioCirugia.findById(id)
                .orElseThrow(()-> new CirugiaNoEncontradaException("No existe ninguna cirugia con aquel ID"));

        buscada.setEstado(cirugiaActualizar.estado());
        buscada.setFecha(cirugiaActualizar.fecha());

        EntidadUsuarios cirujanoNuevo = repositorioUsuario
                .findById(cirugiaActualizar.idCirujano())
                .orElseThrow(()-> new UsuarioNoEncontradoException("No existe ningún cirujano con aquel ID"));
        buscada.setCirujano(cirujanoNuevo);

        EntidadQuirofano quirofanoNuevo = repositorioQuirofano
                .findById(cirugiaActualizar.idQuirofano())
                .orElseThrow(()-> new QuirofanoNoEncontradoException("No existe ningún quirofano con aquel ID"));

        buscada.setQuirofano(quirofanoNuevo);

        System.out.println(buscada);

        EntidadCirugia guardado = repositorioCirugia.save(buscada);

        return cirugiaMapper.toDTO(guardado);
    }

    @Override
    public void borrar(Long id) {
        EntidadCirugia buscada = repositorioCirugia.findById(id)
                .orElseThrow(()-> new CirugiaNoEncontradaException("No existe ninguna cirugia con aquel ID"));

        if(buscada.getEstado().equals(EstadoCirugia.CANCELADA) ||  buscada.getEstado().equals(EstadoCirugia.FINALIZADA)) {
            repositorioCirugia.delete(buscada);
        } else {
            throw new CirugiaEnCursoException("La cirugía está en curso, no se peude eliminar");
        }
    }

}
