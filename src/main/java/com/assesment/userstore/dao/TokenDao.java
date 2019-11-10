package com.assesment.userstore.dao;

import com.assesment.userstore.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenDao extends CrudRepository<Token, Long> {
    Optional<Token> findTokenOptionalByUserId(Long userId);
}