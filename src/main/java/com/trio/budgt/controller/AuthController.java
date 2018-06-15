package com.trio.budgt.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trio.budgt.data.model.User;
import com.trio.budgt.data.repository.UserRepository;
import com.trio.budgt.exception.AuthenticationFailedException;
import com.trio.budgt.exception.UserNotFoundException;
import com.trio.budgt.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
public class AuthController {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${demo.jwt.key}")
    private String key;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public User add(@RequestBody @Validated User user) {
        return userService.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User request) throws Exception{
        User user = userService.findByEmail(request.getEmail());
        if (user == null) {
            throw new AuthenticationFailedException(String.format("user %s not found", request.getEmail()));
        }
        if (user.getPassword().equals(request.getPassword())) {
            Claims claims = Jwts.claims();
            claims.put("user", mapper.writeValueAsString(user));
            log.info(String.format("Loging in %s", user.getEmail()));
            return Jwts.builder().signWith(SignatureAlgorithm.HS256, key).setClaims(claims).compact();
        }
        throw new AuthenticationFailedException("password error");
    }
}
