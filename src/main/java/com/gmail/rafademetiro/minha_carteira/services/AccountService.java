package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.exceptions.ObjectNotFoundException;
import com.gmail.rafademetiro.minha_carteira.models.*;
import com.gmail.rafademetiro.minha_carteira.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

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
            account = this.accountRepository.findById(accountInputDTO.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada, ID:" + accountInputDTO.getId()));

            account.setBalance(accountInputDTO.getBalance());
            account.setRevenues(accountInputDTO.getRevenue());
            account.setExpenses(accountInputDTO.getExpenses());
            account.setMemberSince(accountInputDTO.getMemberSince());
        }

        AccountOutputDTO accountOutputDTO = new AccountOutputDTO(this.accountRepository.save(account));

        return new ResponseEntity<>(accountOutputDTO, HttpStatus.CREATED);
    }

    public Account findById(BigInteger id){
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada ID:" + id));

        System.out.println(account.toString());

        account.getRevenues().stream().forEach(revenue -> {
            revenue.setAccount(null);
        });


        return account;
    }

    public Account findByNumber(String number){

        return this.accountRepository.findByNumber(number);

    }


//    public void addRevenue(RevenueInputDTO revenueInputDTO, Account account) {
//        User user = this.userService.findById(revenueInputDTO.getAccountId());
//        Revenue revenue = new Revenue(revenueInputDTO, account);
//        account.addRevenue(revenue);
//
//        this.accountRepository.save(account);
//    }

    public ResponseEntity<RevenueOutputDTO> addRevenue(RevenueInputDTO revenueInputDTO){
        Account account = this.accountRepository.findById(revenueInputDTO.getAccountId())
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada ID:" + revenueInputDTO.getAccountId()));

        Revenue revenue = new Revenue(LocalDate.now(), revenueInputDTO.getValue(), account);

        List<Revenue> revenues = account.getRevenues();
        revenues.add(revenue);

       RevenueOutputDTO revenueResponse = new RevenueOutputDTO(revenue);

       this.accountRepository.save(account);

       return new ResponseEntity<RevenueOutputDTO>(revenueResponse, HttpStatus.CREATED);

    }

    public Iterable<Account> findAll() {
        return this.accountRepository.findAll();
    }
}
