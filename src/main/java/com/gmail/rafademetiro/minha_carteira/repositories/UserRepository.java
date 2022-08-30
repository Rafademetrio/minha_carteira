package com.gmail.rafademetiro.minha_carteira.repositories;

import com.gmail.rafademetiro.minha_carteira.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface UserRepository extends PagingAndSortingRepository<User, BigInteger> {

}
