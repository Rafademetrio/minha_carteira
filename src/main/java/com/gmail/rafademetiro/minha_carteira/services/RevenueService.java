package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.exceptions.ObjectNotFoundException;
import com.gmail.rafademetiro.minha_carteira.models.*;
import com.gmail.rafademetiro.minha_carteira.repositories.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    public Revenue findById(BigInteger id) {
        return this.revenueRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Revenue not found!! ID: " + id));
    }

    public ResponseEntity<RevenueOutputDTO> save(RevenueInputDTO revenueInputDTO) {
        return accountService.addRevenue(revenueInputDTO);
    }

    public Iterable<RevenueOutputDTO> findByAccountId(BigInteger accountId) {
        Iterable<Revenue> revenueIterable = this.revenueRepository.findByAccountId(accountId);
        List<RevenueOutputDTO> revenueOutputDTOList = new ArrayList<>();

        revenueIterable.forEach(revenue -> revenueOutputDTOList.add(new RevenueOutputDTO(revenue)));
        Iterable<RevenueOutputDTO> iterableRevenueOutputDTO = revenueOutputDTOList;


        return iterableRevenueOutputDTO;
    }


    public ResponseEntity delete(BigInteger id) {
        this.revenueRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deleteByAccountId(BigInteger accountId){
        Iterable<Revenue> revenueIterable = this.revenueRepository.findByAccountId(accountId);
        this.revenueRepository.deleteAll(revenueIterable);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
