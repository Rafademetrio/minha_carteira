package com.gmail.rafademetiro.minha_carteira.models;

import java.math.BigInteger;

public class UserOutputDTO {

    private BigInteger id;

    private String name;

    private String email;

    private Account account;

    public UserOutputDTO() {
    }

    public UserOutputDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.account = user.getAccount();
    }

    public UserOutputDTO(BigInteger id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
