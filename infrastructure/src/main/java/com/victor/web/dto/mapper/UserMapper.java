package com.victor.web.dto.mapper;

import com.victor.entity.User;
import com.victor.web.dto.CreateDto;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(CreateDto dto) {
        return new ModelMapper().map(dto, User.class);
    }

}
