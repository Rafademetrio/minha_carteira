package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.models.*;
import com.gmail.rafademetiro.minha_carteira.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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


    public void addRevenue(RevenueInputDTO revenueInputDTO, Account account) {
        User user = this.userService.findById(revenueInputDTO.getUserId());
        Revenue revenue = new Revenue(revenueInputDTO, user);
        account.addRevenue(revenue);

        this.accountRepository.save(account);
    }

}
