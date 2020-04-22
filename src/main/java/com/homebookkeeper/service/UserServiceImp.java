package com.homebookkeeper.service;

import com.homebookkeeper.model.User;
import com.homebookkeeper.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepo.findById(id);
    }


    @Override
    public Optional<User> getByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    @Override
    public User save(User user) {
     return  userRepo.save(user);
    }

    @Override
    public void delete(long id) {

    }

}
