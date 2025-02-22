package com.nhnacademy.mini_dooray.task.config;

import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.exception.*;
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

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleTaskNotFoundException() {
        return ResponseEntity.status(NOT_FOUND).body(new ResponseMessage("task is not found"));
    }

    @ExceptionHandler(InvalidProjectStatusException.class)
    public ResponseEntity<ResponseMessage> handleInvalidProjectStatusException(InvalidProjectStatusException e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleTagNotFoundException(TagNotFoundException e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(TagDuplicateException.class)
    public ResponseEntity<ResponseMessage> handleTagDuplicateException(TagDuplicateException e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleException(Exception e) {
        ResponseMessage errorMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorMessage);
    }
}
