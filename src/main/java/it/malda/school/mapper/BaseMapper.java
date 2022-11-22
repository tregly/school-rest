package it.malda.school.mapper;

import java.util.ArrayList;
import java.util.List;

abstract interface BaseMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDto(List<E> entities) {
        List<D> dtos = new ArrayList<>();
        entities.forEach(x -> {
            dtos.add(this.toDto(x));
        });
        return dtos;
    }

    default List<E> toEntity(List<D> dtos) {
        List<E> entities = new ArrayList<>();
        dtos.forEach(x -> {
            entities.add(this.toEntity(x));
        });
        return entities;
    }
}
