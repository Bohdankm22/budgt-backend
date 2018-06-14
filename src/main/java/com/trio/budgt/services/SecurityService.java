package com.trio.budgt.services;

import com.trio.budgt.data.model.User;

public interface SecurityService {
    User getPrincipal();

    boolean hasRole(String... roles);
}