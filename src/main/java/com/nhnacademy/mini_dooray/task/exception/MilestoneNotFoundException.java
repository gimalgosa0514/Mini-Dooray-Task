package com.nhnacademy.mini_dooray.task.exception;

public class MilestoneNotFoundException extends RuntimeException {
    public MilestoneNotFoundException(){}
    public MilestoneNotFoundException(String message) {
        super(message);
    }
}
