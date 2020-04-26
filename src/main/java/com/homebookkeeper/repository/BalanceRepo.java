package com.homebookkeeper.repository;

import com.homebookkeeper.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepo extends JpaRepository<Balance, Long> {
}
