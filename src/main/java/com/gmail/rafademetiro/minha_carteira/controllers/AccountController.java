package com.gmail.rafademetiro.minha_carteira.controllers;

import com.gmail.rafademetiro.minha_carteira.models.Account;
import com.gmail.rafademetiro.minha_carteira.models.AccountInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.AccountOutputDTO;
import com.gmail.rafademetiro.minha_carteira.repositories.AccountRepository;
import com.gmail.rafademetiro.minha_carteira.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountOutputDTO> createAccount(@RequestBody AccountInputDTO accountInputDTO){
        return this.accountService.save(accountInputDTO);
    }

    @GetMapping(path = "/{number}")
    public Account findByNumber(@PathVariable String number){
        return this.accountService.findByNumber(number);
    }

    @GetMapping(path = "/findById")
    public Account findById(@RequestParam int id){
        BigInteger identification = BigInteger.valueOf(id);
        return this.accountService.findById(identification);
    }


}
