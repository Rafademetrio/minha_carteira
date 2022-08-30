package com.gmail.rafademetiro.minha_carteira.services;

import com.gmail.rafademetiro.minha_carteira.exceptions.ObjectNotFoundException;
import com.gmail.rafademetiro.minha_carteira.repositories.UserRepository;
import com.gmail.rafademetiro.minha_carteira.models.User;
import com.gmail.rafademetiro.minha_carteira.models.UserInputDTO;
import com.gmail.rafademetiro.minha_carteira.models.UserOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private ExpenseService expenseService;
//
//    @Autowired
//    private RevenueService revenueService;

    public Iterable<User> findAll(){
        return this.userRepository.findAll();
    }

    //save or update
    public User findById(BigInteger id){
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException("User not found! ID: " + id));
    }

    public ResponseEntity<UserOutputDTO> save(UserInputDTO userInputDTO){

        User user;

        if(userInputDTO.getId() == null){
            user = createUser(userInputDTO);
        }else{
            User userDB = this.findById(userInputDTO.getId());
            user = updateUserAttributes(userInputDTO, userDB);
        }

        User savedUser = this.userRepository.save(user);
        UserOutputDTO userResponse = new UserOutputDTO(savedUser);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    public ResponseEntity deleteById(BigInteger id){
        //this.expenseService.deleteByUserId(id);
        //this.revenueService.deleteByUserId(id);

        this.userRepository.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }


    private User createUser(UserInputDTO userInputDTO){
        return new User(userInputDTO.getName(), userInputDTO.getEmail(), userInputDTO.getPassword());
    }

    private User updateUserAttributes(UserInputDTO userInputDTO, User user){
        user.setEmail(userInputDTO.getEmail());
        user.setName(userInputDTO.getName());
        user.setPassword(user.getPassword());

        return user;
    }
}
