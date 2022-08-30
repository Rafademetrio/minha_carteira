package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.exceptions.ObjectNotFoundException;
import com.gmail.rafademetiro.minha_carteira.models.Revenue;
import com.gmail.rafademetiro.minha_carteira.models.RevenueInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.RevenueOutputDTO;
import com.gmail.rafademetiro.minha_carteira.models.User;
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
        User user = this.userService.findById(revenueInputDTO.getUserId());
        Revenue revenue = new Revenue(LocalDate.now(), revenueInputDTO.getValue(), user);
        this.revenueRepository.save(revenue);

        accountService.addRevenue(revenueInputDTO, user.getAccount());

        RevenueOutputDTO revenueResponse = new RevenueOutputDTO(revenue);

        return new ResponseEntity<>(revenueResponse, HttpStatus.CREATED);
    }

    public Iterable<RevenueOutputDTO> findByUserId(BigInteger userId) {
        Iterable<Revenue> revenueIterable = this.revenueRepository.findByUserId(userId);
        List<RevenueOutputDTO> revenueOutputDTOList = new ArrayList<>();

        revenueIterable.forEach(revenue -> revenueOutputDTOList.add(new RevenueOutputDTO(revenue)));
        Iterable<RevenueOutputDTO> iterableRevenueOutputDTO = revenueOutputDTOList;


        return iterableRevenueOutputDTO;
    }


    public ResponseEntity delete(BigInteger id) {
        this.revenueRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deleteByUserId(BigInteger userId){
        Iterable<Revenue> revenueIterable = this.revenueRepository.findByUserId(userId);
        this.revenueRepository.deleteAll(revenueIterable);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
