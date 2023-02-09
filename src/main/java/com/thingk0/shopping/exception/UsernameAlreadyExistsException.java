package com.thingk0.shopping.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super("이미 존재하는 아이디입니다.");
    }
}

