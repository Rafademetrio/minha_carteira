package com.gmail.rafademetiro.minha_carteira.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(unique = true)
    private String number;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    private LocalDate memberSince;

    private BigDecimal balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Revenue> revenues;

    public Account() {
    }

    public Account(AccountInputDTO accountInputDTO) {
        this.id = accountInputDTO.getId();
        this.number = accountInputDTO.getNumber();
        this.balance = accountInputDTO.getBalance();
        this.memberSince = accountInputDTO.getMemberSince();
        this.expenses = accountInputDTO.getExpenses();
        this.revenues = accountInputDTO.getRevenue();

    }

    public Account(BigInteger id, String number, LocalDate memberSince, BigDecimal balance) {
        this.id = id;
        this.number = number;
        this.memberSince = memberSince;
        this.balance = balance;
    }

    public Account(BigInteger id, String number, LocalDate memberSince, BigDecimal balance, List<Expense> expenses, List<Revenue> revenues) {
        this.id = id;
        this.number = number;
        this.memberSince = memberSince;
        this.balance = balance;
        this.expenses = expenses;
        this.revenues = revenues;
    }

    public Account(BigInteger id, String number, User user, LocalDate memberSince, BigDecimal balance, List<Expense> expenses, List<Revenue> revenues) {
        this.id = id;
        this.number = number;
        this.user = user;
        this.memberSince = memberSince;
        this.balance = balance;
        this.expenses = expenses;
        this.revenues = revenues;
    }

    public void addRevenue(Revenue revenue){
        this.balance = this.balance.add(revenue.getAmount());
        this.revenues.add(revenue);
    }

    public void addExpense(Expense expense){
        this.balance = this.balance.subtract(expense.getAmount());
        this.expenses.add(expense);
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Revenue> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<Revenue> revenues) {
        this.revenues = revenues;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
