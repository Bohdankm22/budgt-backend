package com.trio.budgt.services;

import com.trio.budgt.data.model.Role;
import com.trio.budgt.data.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService{

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    public User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean hasRole(String... roles) {
        Set<String> require = new HashSet<>(Arrays.asList(roles));
        require.stream().forEach(System.out::println);
        User user = getPrincipal();
        Set<String> mine = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        mine.stream().forEach(System.out::println);
        for(String req: require) {
            if (mine.contains(req)) {
                return true;
            }
        }
        return false;
    }
}