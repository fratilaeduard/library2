package com.example.library2.exception;

import com.example.library2.entity.User;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
