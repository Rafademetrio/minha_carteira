package com.gmail.rafademetiro.minha_carteira.controllers;

import com.gmail.rafademetiro.minha_carteira.exceptions.ObjectNotFoundException;
import com.gmail.rafademetiro.minha_carteira.models.Account;
import com.gmail.rafademetiro.minha_carteira.services.AccountService;
import com.gmail.rafademetiro.minha_carteira.services.UserService;
import com.gmail.rafademetiro.minha_carteira.models.User;
import com.gmail.rafademetiro.minha_carteira.models.UserInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.UserOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/all")
    public Iterable<User> findAll(){
        return this.userService.findAll();
    }

    @GetMapping(path = "/findById")
    public User findById(@RequestBody UserInputDTO userInputDTO){
        return this.userService.findById(userInputDTO.getId());
    }

    @PostMapping(path = "/findByName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public List<User> findByName(@RequestParam String name){
        List<User> users = new ArrayList<>();
        Optional<User> userOptional = this.userService.findByName(name);
        if(userOptional.isPresent()){
            users.add(userOptional.get());
            return users;
        }else{
            users = this.userService.findByNameContaining(name);
            if (users.isEmpty()){
                throw new ObjectNotFoundException("Não foi encontrado nenhum usário com esse nome");
            }
            return users;
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<UserOutputDTO> save(@ModelAttribute UserInputDTO userInputDTO){
        return this.userService.save(userInputDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody UserInputDTO userInputDTO){
        return this.userService.deleteById(userInputDTO.getId());
    }

    @PostMapping("/createWithAccount")
    public ResponseEntity<UserOutputDTO> createUserWithAccount(@ModelAttribute UserInputDTO userInputDTO, @RequestParam @NotNull String accountNumber){

        System.out.println("Esse e o numero da conta : " + accountNumber);

        User user = new User(userInputDTO);


        Account account = this.accountService.findAccoundByNumber(accountNumber);

        user.setAccount(account);



        return this.userService.save(user);
    }



}
