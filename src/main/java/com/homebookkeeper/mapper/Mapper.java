package com.homebookkeeper.mapper;

import com.homebookkeeper.DTO.BaseDTO;
import com.homebookkeeper.model.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends BaseDTO> {
    E toEntity(D dto);

    D toDTO(E entity);
}
