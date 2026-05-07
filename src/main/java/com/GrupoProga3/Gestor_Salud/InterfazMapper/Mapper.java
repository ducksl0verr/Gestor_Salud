package com.GrupoProga3.Gestor_Salud.InterfazMapper;



public interface Mapper{

    D convertToDto(E entity);
    E convertToEntity(D dto);
}
