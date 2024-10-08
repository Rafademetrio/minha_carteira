package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.models.*;
import com.gmail.rafademetiro.minha_carteira.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserService userService;

    public ResponseEntity<AccountOutputDTO> save(AccountInputDTO accountInputDTO) {
        Account account;
        if(accountInputDTO.getId() == null){
            account = new Account(accountInputDTO);
            account.setMemberSince(LocalDate.now());
        } else {
            account = this.accountRepository.findByNumber(accountInputDTO.getNumber());
        }

        AccountOutputDTO accountOutputDTO = new AccountOutputDTO(this.accountRepository.save(account));

        return new ResponseEntity<>(accountOutputDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<AccountOutputDTO> findByNumber(String number){
        AccountOutputDTO accountResponse = new AccountOutputDTO(this.accountRepository.findByNumber(number));

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    public ResponseEntity<AccountOutputDTO> findById(BigInteger id){
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if(accountOptional.isPresent()){
            AccountOutputDTO accountResponse = new AccountOutputDTO(accountOptional.get());
            return new ResponseEntity<>(accountResponse, HttpStatus.OK);
        }else{
            System.out.println("Any account found.");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    public Account findAccoundById(BigInteger id){
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if(accountOptional.isPresent()){
            return  accountOptional.get();
        }else{
            System.out.println("Any account found.");
            return null;
        }
    }


    public void addRevenue(RevenueInputDTO revenueInputDTO, Account account) {
        Revenue revenue = new Revenue(revenueInputDTO, account);
        account.addRevenue(revenue);

        this.accountRepository.save(account);
    }

    //TODO (implement addExpense)

}
