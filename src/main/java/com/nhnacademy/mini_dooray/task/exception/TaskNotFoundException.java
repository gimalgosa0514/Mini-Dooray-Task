package com.nhnacademy.mini_dooray.task.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){}
    public TaskNotFoundException(String msg){
        super(msg);
    }
}
