package com.gmail.rafademetiro.minha_carteira.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class RevenueInputDTO {

    private BigInteger id;

    private LocalDate date;

    @NotNull
    private BigDecimal value;

    @NotNull
    @Min(0)
    private BigInteger accountId;

    public RevenueInputDTO() {
    }

    public RevenueInputDTO(Revenue revenue) {
        this.id = revenue.getId();
        this.date = revenue.getDate();
        this.value = revenue.getAmount();
        this.accountId = revenue.getAccount().getId();
    }

    public RevenueInputDTO(BigInteger id, LocalDate date, BigDecimal value, BigInteger accountId) {
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

    public @NotNull @Min(0) BigInteger getAccountId() {
        return accountId;
    }

    public void setAccountId(@NotNull @Min(0) BigInteger accountId) {
        this.accountId = accountId;
    }
}
