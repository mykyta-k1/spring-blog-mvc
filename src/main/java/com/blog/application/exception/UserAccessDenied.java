package com.blog.application.exception;

public class UserAccessDenied extends RuntimeException {

    public UserAccessDenied(String message) {
        super(message);
    }
}
