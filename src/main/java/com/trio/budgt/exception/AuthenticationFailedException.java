package com.trio.budgt.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailedException extends AuthenticationException {
    public AuthenticationFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}
