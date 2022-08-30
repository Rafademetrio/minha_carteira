package com.gmail.rafademetiro.minha_carteira.repositories;

import com.gmail.rafademetiro.minha_carteira.models.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface AccountRepository extends PagingAndSortingRepository<Account, BigInteger> {

    public Account findByNumber(String number);
}
