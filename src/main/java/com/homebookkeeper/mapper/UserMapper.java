package com.homebookkeeper.mapper;

import com.homebookkeeper.DTO.UserDTO;
import com.homebookkeeper.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    public UserMapper() {
        super(User.class, UserDTO.class);
    }
}
