package com.gmail.rafademetiro.minha_carteira.models;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class AccountOutputDTO {

    private BigInteger id;

    private String number;

    private LocalDate memberSince;

    private List<Expense> expenses;

    private List<Revenue> revenue;

    public AccountOutputDTO() {
    }

    public AccountOutputDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.memberSince = account.getMemberSince();
        this.expenses = account.getExpenses();
        this.revenue = account.getRevenues();
    }

    public AccountOutputDTO(BigInteger id, String number, LocalDate memberSince, List<Expense> expenses, List<Revenue> revenue) {
        this.id = id;
        this.number = number;
        this.memberSince = memberSince;
        this.expenses = expenses;
        this.revenue = revenue;
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

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Revenue> getRevenue() {
        return revenue;
    }

    public void setRevenue(List<Revenue> revenue) {
        this.revenue = revenue;
    }
}
