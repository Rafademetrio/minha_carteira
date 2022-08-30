package com.gmail.rafademetiro.minha_carteira.controllers;

import com.gmail.rafademetiro.minha_carteira.models.ExpenseInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.ExpenseOutputDTO;
import com.gmail.rafademetiro.minha_carteira.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(path = "/findByUserId")
    public Iterable<ExpenseOutputDTO> findByUserId(@RequestBody ExpenseInputDTO expenseInputDTO) {
        return this.expenseService.findByUserId(expenseInputDTO.getUserId());
    }

    @PostMapping
    public ResponseEntity<ExpenseOutputDTO> save(@Valid @RequestBody ExpenseInputDTO expenseInputDTO) {
        return this.expenseService.save(expenseInputDTO);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody ExpenseInputDTO expenseInputDTO) {
        return this.expenseService.delete(expenseInputDTO);
    }

    @DeleteMapping(path = "/deleteByUserId")
    public ResponseEntity deleteByUserId(@RequestBody ExpenseInputDTO expenseInputDTO) {
       return this.expenseService.deleteByUserId(expenseInputDTO.getUserId());
    }

}
