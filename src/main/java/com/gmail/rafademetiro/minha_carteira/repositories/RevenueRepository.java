package com.gmail.rafademetiro.minha_carteira.repositories;

import com.gmail.rafademetiro.minha_carteira.models.Revenue;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface RevenueRepository extends PagingAndSortingRepository<Revenue, BigInteger> {
    Iterable<Revenue> findByAccountId(BigInteger accountId);
}
