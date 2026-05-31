package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaActualizar;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaNueva;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.DTOs.HistoriaClinicaRespuesta;
import com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.EntidadHistoriaClinica;
import com.GrupoProga3.Gestor_Salud.Pacientes.Model.EntidadPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface  HistoriaClinicaMapper {
    //EntidadHistoriaClinica toEntity (HistoriaClinicaActualizar historiaClinicaActualizar);

    @Mapping(target="paciente", ignore = true)
    @Mapping(target="profesional", ignore = true)
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

    @Mapping(source="paciente.id", target="id_paciente")
    @Mapping(source="profesional.id", target="id_profesional")
    HistoriaClinicaRespuesta toDTO (EntidadHistoriaClinica historiaClinica);
}
