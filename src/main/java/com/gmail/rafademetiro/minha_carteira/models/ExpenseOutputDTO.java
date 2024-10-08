package com.gmail.rafademetiro.minha_carteira.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class ExpenseOutputDTO {

    private BigInteger id;

    private LocalDate date;

    private BigDecimal value;

    private BigInteger accountId;

    public ExpenseOutputDTO(Expense expense) {
        this.id = expense.getId();
        this.date = expense.getDate();
        this.value = expense.getAmount();
        this.accountId = expense.getAccount().getId();
    }

    public ExpenseOutputDTO() {
    }

    public ExpenseOutputDTO(BigInteger id, LocalDate date, BigDecimal value, BigInteger accountId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.accountId = accountId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigInteger getAccountId() {
        return accountId;
    }

    public void setAccountId(BigInteger accountId) {
        this.accountId = accountId;
    }
}
