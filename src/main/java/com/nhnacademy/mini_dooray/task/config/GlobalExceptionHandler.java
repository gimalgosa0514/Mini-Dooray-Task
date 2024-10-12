package com.nhnacademy.mini_dooray.task.config;

import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.exception.InvalidProjectStatusException;
import com.nhnacademy.mini_dooray.task.exception.NoProjectFoundByMemberException;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchProjectFoundException.class)
    public ResponseEntity<ResponseMessage> handleNoSuchProjectFoundException(NoSuchProjectFoundException e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(NoProjectFoundByMemberException.class)
    public ResponseEntity<ResponseMessage> handleNoProjectByMemberException(NoProjectFoundByMemberException e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InvalidProjectStatusException.class)
    public ResponseEntity<ResponseMessage> handleInvalidProjectStatusException(InvalidProjectStatusException e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorMessage);
    }
}
