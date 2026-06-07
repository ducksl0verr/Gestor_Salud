package com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoNuevo;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.DTOs.MedicamentoRespuesta;
import com.GrupoProga3.Gestor_Salud.Medicamentos.Dominio.EntidadMedicamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {

    EntidadMedicamento toEntity (MedicamentoNuevo  medicamentoNuevo);
    MedicamentoRespuesta toDTO (EntidadMedicamento entidadMedicamento);

}