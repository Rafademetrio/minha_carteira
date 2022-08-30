package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.exceptions.ObjectNotFoundException;
import com.gmail.rafademetiro.minha_carteira.models.*;
import com.gmail.rafademetiro.minha_carteira.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    public Expense findById(BigInteger id) {
        return this.expenseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Expense not found!! ID: " + id));
    }

    public Iterable<ExpenseOutputDTO> findByUserId(BigInteger userId) {
        Iterable<Expense> expenseIterable = this.expenseRepository.findByUserId(userId);
        List<ExpenseOutputDTO> expenseOutputDTOList = new ArrayList<>();

        expenseIterable.forEach(expense -> expenseOutputDTOList.add(new ExpenseOutputDTO(expense)));

        Iterable<ExpenseOutputDTO> expenseOutputDTOIterable = expenseOutputDTOList;

        return expenseOutputDTOIterable;
    }

    public ResponseEntity<ExpenseOutputDTO> save(ExpenseInputDTO expenseInputDTO) {
        User user = userService.findById(expenseInputDTO.getUserId());
        Expense expense = new Expense(LocalDate.now(), expenseInputDTO.getValue(), user);

        ExpenseOutputDTO expenseResponse = new ExpenseOutputDTO(this.expenseRepository.save(expense));

        return new ResponseEntity<ExpenseOutputDTO>(expenseResponse, HttpStatus.CREATED);
    }

    public ResponseEntity delete(ExpenseInputDTO expenseInputDTO){
        expenseRepository.delete(this.findById(expenseInputDTO.getId()));
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity deleteByUserId(BigInteger userId){
        Iterable<Expense> expenseIterable = expenseRepository.findByUserId(userId);
        expenseRepository.deleteAll(expenseIterable);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
