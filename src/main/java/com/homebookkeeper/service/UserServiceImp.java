package com.homebookkeeper.service;

import com.homebookkeeper.model.Balance;
import com.homebookkeeper.model.CurrencyCodes;
import com.homebookkeeper.model.User;
import com.homebookkeeper.repository.BalanceRepo;
import com.homebookkeeper.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepo userRepo;
    private BalanceRepo balanceRepo;

    public UserServiceImp(UserRepo userRepo, BalanceRepo balanceRepo) {
        this.userRepo = userRepo;
        this.balanceRepo = balanceRepo;
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
     user = userRepo.save(user);
     balanceRepo.save(new Balance(user, CurrencyCodes.RUB));
        return user;
    }

    @Override
    public void delete(long id) {

    }

}
