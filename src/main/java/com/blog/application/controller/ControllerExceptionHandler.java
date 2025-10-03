package com.blog.application.controller;

import com.blog.application.exception.UserAlreadyExistsException;
import com.blog.application.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public String userAlreadyExists(UserAlreadyExistsException ex) {
        return "login";
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public String userNotFound(UserNotFoundException ex) {
        return "login";
    }
}
