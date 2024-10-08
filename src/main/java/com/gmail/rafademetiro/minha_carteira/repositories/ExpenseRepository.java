package com.gmail.rafademetiro.minha_carteira.repositories;

import com.gmail.rafademetiro.minha_carteira.models.Expense;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, BigInteger> {

    Iterable<Expense> findByAccountId(BigInteger accountId);

    void deleteByAccountId(BigInteger accountId);

}
