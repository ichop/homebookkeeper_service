package com.homebookkeeper.service;

import com.homebookkeeper.model.Balance;
import com.homebookkeeper.repository.BalanceRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {
    BalanceRepo balanceRepo;

    public BalanceServiceImpl(BalanceRepo balanceRepo) {
        this.balanceRepo = balanceRepo;
    }

    @Override
    public Optional<Balance> getById(Long id) {
        return balanceRepo.findById(id);
    }

    @Override
    public Balance save(Balance balance) {
        return balanceRepo.save(balance);
    }
}
