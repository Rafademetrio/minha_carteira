package com.gmail.rafademetiro.minha_carteira.controllers;

import com.gmail.rafademetiro.minha_carteira.services.UserService;
import com.gmail.rafademetiro.minha_carteira.models.User;
import com.gmail.rafademetiro.minha_carteira.models.UserInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.UserOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public Iterable<User> findAll(){
        return this.userService.findAll();
    }

    @GetMapping(path = "/findById")
    public User findById(@RequestBody UserInputDTO userInputDTO){
        return this.userService.findById(userInputDTO.getId());
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> save(@Valid @RequestBody UserInputDTO userInputDTO){
        return this.userService.save(userInputDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody UserInputDTO userInputDTO){
        return this.userService.deleteById(userInputDTO.getId());
    }



}
