package com.homebookkeeper.service;

import com.homebookkeeper.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    Optional<User> getById(long id);

    Optional<User> getByEmail(String email);

    User save(User user);

    void delete(long id);

}
