package com.homebookkeeper.restcontroller;

import com.homebookkeeper.DTO.BalanceDTO;
import com.homebookkeeper.mapper.BalanceMapper;
import com.homebookkeeper.model.Balance;
import com.homebookkeeper.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BalanceController {

    private final BalanceService balanceService;
    private final BalanceMapper balanceMapper;


    public BalanceController(BalanceService balanceService, BalanceMapper balanceMapper) {
        this.balanceService = balanceService;
        this.balanceMapper = balanceMapper;
    }


    @GetMapping(path = "/user/balance")
    public ResponseEntity<BalanceDTO> getBalance(@RequestParam Long id) {
        System.out.println(balanceService.getById(id));
        return new ResponseEntity<BalanceDTO>(balanceMapper.toDTO(balanceService.getById(id).orElse(null)), HttpStatus.OK);
    }

    @PutMapping(path = "/user/balance")
    public ResponseEntity<BalanceDTO> updateDTO(@RequestBody BalanceDTO balanceDTO) {
        if (balanceDTO != null) {
            Balance balance = balanceService.save(balanceMapper.toEntity(balanceDTO));
            if (balance != null) {
                return new ResponseEntity<BalanceDTO>(balanceMapper.toDTO(balance), HttpStatus.OK);
            }
        }
        return new ResponseEntity<BalanceDTO>(HttpStatus.BAD_REQUEST);
    }

}
