package com.homebookkeeper.service;

import com.homebookkeeper.model.Balance;

import java.util.Optional;

public interface BalanceService {
    Optional<Balance> getById(Long id);
}
