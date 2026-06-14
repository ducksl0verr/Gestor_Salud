package com.GrupoProga3.Gestor_Salud.HistoriaClinica.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.DTOs.DiagnosticoRespuesta;
import com.GrupoProga3.Gestor_Salud.Diagnosticos.Dominio.EntidadDiagnostico;
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
    @Mapping(target = "diagnosticos", ignore = true)
    EntidadHistoriaClinica toEntity (HistoriaClinicaNueva historiaClinicaNueva);

    @Mapping(source="paciente.id", target="id_paciente")
    @Mapping(source="profesional.id", target="id_profesional")
    HistoriaClinicaRespuesta toDTO (EntidadHistoriaClinica historiaClinica);

    @Mapping(target = "idHistoriaClinica",
            source = "historiaClinica.id")
    DiagnosticoRespuesta toDTO (EntidadDiagnostico entidadDiagnostico);

    //HistoriaClinicaRespuesta toDTO (HistoriaClinicaNueva historiaClinicaNueva);
}
