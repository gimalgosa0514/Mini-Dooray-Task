package com.nhnacademy.mini_dooray.task.config;

import com.nhnacademy.mini_dooray.task.domain.ErrorMessage;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchProjectFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchProjectFoundException(NoSuchProjectFoundException e) {
        ErrorMessage errorResponse = new ErrorMessage(e.getMessage(), 404);
        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }
}
