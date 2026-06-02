package com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.MAPPER;

import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoNuevo;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.DTOs.TratamientoRespuesta;
import com.GrupoProga3.Gestor_Salud.Tratamientos.Doiminio.EntidadTratamiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TratamientoMapper {
    EntidadTratamiento toEntity (TratamientoNuevo tratamientoNuevo);
    TratamientoRespuesta toDTO (EntidadTratamiento entidadTratamiento);
}
