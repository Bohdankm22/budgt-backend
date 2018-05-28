package com.trio.budgt.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super(String.format("Could not user with id %d", userId));
    }

}
