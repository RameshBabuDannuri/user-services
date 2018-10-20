package com.ramesh.userservice.exception;

import com.ramesh.userservice.model.User;
import org.springframework.http.ResponseEntity;

public class UserNotCreateException extends RuntimeException {
    public UserNotCreateException(String msg) {
        super(msg);
    }
}
