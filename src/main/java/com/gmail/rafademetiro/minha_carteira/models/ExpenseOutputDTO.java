package com.gmail.rafademetiro.minha_carteira.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class ExpenseOutputDTO {

    private BigInteger id;

    private LocalDate date;

    private BigDecimal value;

    private BigInteger userId;

    public ExpenseOutputDTO(Expense expense) {
        this.id = expense.getId();
        this.date = expense.getDate();
        this.value = expense.getValue();
        this.userId = expense.getUser().getId();
    }

    public ExpenseOutputDTO() {
    }

    public ExpenseOutputDTO(BigInteger id, LocalDate date, BigDecimal value, BigInteger userId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.userId = userId;
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

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
}
