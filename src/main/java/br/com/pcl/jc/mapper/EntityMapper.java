package br.com.pcl.jc.mapper;

import java.util.List;
import java.util.Set;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    Set<D> toDTO(Set<E> entityList);
}
