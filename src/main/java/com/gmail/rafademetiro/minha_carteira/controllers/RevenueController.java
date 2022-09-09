package com.gmail.rafademetiro.minha_carteira.controllers;

import com.gmail.rafademetiro.minha_carteira.models.Revenue;
import com.gmail.rafademetiro.minha_carteira.models.RevenueInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.RevenueOutputDTO;
import com.gmail.rafademetiro.minha_carteira.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;


    @GetMapping(path = "/findByAccountId")
    public Iterable<RevenueOutputDTO> findByAccountId(@RequestBody RevenueInputDTO revenueInputDTO) {
        return this.revenueService.findByAccountId(revenueInputDTO.getAccountId());
    }

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

    @DeleteMapping(path = "/deleteByAccountId")
    public ResponseEntity deleteByAccountId(@RequestBody RevenueInputDTO revenueInputDTO) {
        return this.revenueService.deleteByAccountId(revenueInputDTO.getAccountId());
    }


}
