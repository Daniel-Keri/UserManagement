package com.stages.stage1.exc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminUserNotFoundExceptionHandler {

    @ExceptionHandler(AdminUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AdminUserNotFoundException handleWebsiteUserNotFoundExcepton(AdminUserNotFoundException e) {
        return e;
    }
}