package com.trio.budgt.exception;

public class UserDataNotFoundException extends RuntimeException {

    public UserDataNotFoundException(Long userId) {
        super(String.format("Could not find data for user with id %d", userId));
    }

}
