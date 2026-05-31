package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.Pacientes.EntidadPaciente;
import com.GrupoProga3.Gestor_Salud.Usuarios.EntidadUsuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface HistoriaClinicaMapper {
    //EntidadHistoriaClinica toEntity (HistoriaClinicaActualizar historiaClinicaActualizar);

    @Mapping(target="id_paciente", ignore = true)
    @Mapping(target="id_profesional", ignore = true)
    EntidadHistoriaClinica toEntity (HistoriaClinicaNueva historiaClinicaNueva);

///  Estos métodos eran para asegurar el mapeo de los ids del dto a las entidades, pero por el momento no parencen necesarios.
//    default EntidadUsuarios Usuariomap(Long id){
//        if (id==null){
//            return null;
//        }
//        EntidadUsuarios entidadUsuarios = new EntidadUsuarios();
//        entidadUsuarios.setId(id);
//        return entidadUsuarios;
//    }
//
//    default EntidadPaciente Pacientemap (Long id){
//        if (id==null){
//            return null;
//        }
//        EntidadPaciente entidadPaciente = new EntidadPaciente();
//        entidadPaciente.setId(id);
//        return entidadPaciente;
//    }

    @Mapping(source="id_paciente.id", target="id_paciente")
    @Mapping(source="id_profesional.id", target="id_profesional")
    HistoriaClinicaRespuesta toDTO (EntidadHistoriaClinica historiaClinica);

    //HistoriaClinicaRespuesta toDTO (HistoriaClinicaNueva historiaClinicaNueva);
}
