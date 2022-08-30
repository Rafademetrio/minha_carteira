package com.gmail.rafademetiro.minha_carteira.models;

import com.gmail.rafademetiro.minha_carteira.models.User;
import com.gmail.rafademetiro.minha_carteira.services.UserService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JoinColumn(name = "user_id")
    private User user;

    public Revenue() {
    }

    public Revenue(RevenueInputDTO revenueInputDTO, User user) {
        this.id = revenueInputDTO.getId();
        this.date = revenueInputDTO.getDate();
        this.value = revenueInputDTO.getValue();
        this.user = user;
    }

    public Revenue(LocalDate date, BigDecimal value, User user) {
        this.date = date;
        this.value = value;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
