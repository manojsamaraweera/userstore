package com.assesment.userstore.api;

import com.assesment.userstore.dao.TokenDao;
import com.assesment.userstore.dao.UserDao;
import com.assesment.userstore.entity.Token;
import com.assesment.userstore.entity.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableJpaRepositories
@Log
public class UserDatabaseController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @RequestMapping(value = "/users/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String username) {
        log.info("get users called for username " + username);

        final Optional<User> user = userDao.findByUsername(username);

        if (!user.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            log.info("User found for username: " + username);
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("create users called for");
        User savedUser = userDao.save(user);
        if (savedUser == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            log.info("User created: " + user.getUsername());
            return new ResponseEntity(savedUser, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/users/{username}/tokens",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getToken(@PathVariable String username) {
        log.info("get token called for username " + username);

        final Optional<User> user = userDao.findByUsername(username);
        if (user.isPresent()) {
            final Optional<Token> token
                    = tokenDao.findTokenOptionalByUserId(user.get().getId());

            if (!token.isPresent()) {
                log.info("No tokens found for user: " + username);
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            } else {
                log.info("Token found for user: " + username);
                return new ResponseEntity(token, HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/users/{username}/tokens",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createToken(@PathVariable String username,
                                            @RequestBody Token token) {
        log.info("create token called for user " + username);

        final Optional<User> user = userDao.findByUsername(username);
        if (user.isPresent()) {
            token.setUserId(user.get().getId());
            Token savedToken = tokenDao.save(token);
            if (savedToken == null) {
                log.info("No tokens created for user: " + username);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.info("Token created for user: " + username);
                return new ResponseEntity(savedToken, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}