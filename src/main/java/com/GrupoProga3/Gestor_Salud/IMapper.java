package com.GrupoProga3.Gestor_Salud;

public interface IMapper <E, D> {
    D convertToDto(E entity);
    E convertToEntity(D dto);
}

