package com.gmail.rafademetiro.minha_carteira.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;

    private LocalDate date;


    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Revenue() {
    }

    public Revenue(LocalDate date, BigDecimal value, Account account) {
        this.date = date;
        this.value = value;
        this.account = account;
    }

    public Revenue(RevenueInputDTO revenueInputDTO, Account account) {
        this.id = revenueInputDTO.getId();
        this.date = revenueInputDTO.getDate();
        this.value = revenueInputDTO.getValue();
        this.account = account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}
