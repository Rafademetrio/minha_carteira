package com.gmail.rafademetiro.minha_carteira.controllers;

import com.gmail.rafademetiro.minha_carteira.models.ExpenseInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.Revenue;
import com.gmail.rafademetiro.minha_carteira.models.RevenueInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.RevenueOutputDTO;
import com.gmail.rafademetiro.minha_carteira.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping(path = "/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;


//    @GetMapping(path = "/findByUserId")
//    public Iterable<RevenueOutputDTO> findByUserId(@RequestBody RevenueInputDTO revenueInputDTO) {
//        return this.revenueService.findByUserId(revenueInputDTO.getUserId());
//    }

    @GetMapping
    public Revenue findById(@RequestBody RevenueInputDTO revenueInputDTO) {
        return this.revenueService.findById(revenueInputDTO.getId());
    }

    @PostMapping
    public ResponseEntity<RevenueOutputDTO> save(@Valid @RequestBody RevenueInputDTO revenueInputDTO) {
        return this.revenueService.save(revenueInputDTO);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody RevenueInputDTO revenueInputDTO) {
        return this.revenueService.delete(revenueInputDTO.getId());
    }

//    @DeleteMapping(path = "/deleteByUserId")
//    public ResponseEntity deleteByUserId(@RequestBody RevenueInputDTO revenueInputDTO) {
//        return this.revenueService.deleteByUserId(revenueInputDTO.getUserId());
//    }


}
