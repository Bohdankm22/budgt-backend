package com.trio.budgt.auth.controller;

import com.trio.budgt.data.model.User;
import com.trio.budgt.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new User
    @PostMapping("/register")
    public User createUser(@Valid @RequestBody User user) {
        log.info(String.format("Registering user with email %s", user.getEmail()));
        return userRepository.save(user);
    }

}