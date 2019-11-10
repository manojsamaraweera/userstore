package com.assesment.userstore.dao;

import com.assesment.userstore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
}
