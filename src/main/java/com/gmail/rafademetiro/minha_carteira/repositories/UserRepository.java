package com.gmail.rafademetiro.minha_carteira.repositories;

import com.gmail.rafademetiro.minha_carteira.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, BigInteger> {

    Optional<User> findByName(String name);
    List<User> findByNameContaining(String name);
}
