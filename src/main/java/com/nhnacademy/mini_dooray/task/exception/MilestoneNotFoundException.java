package com.nhnacademy.mini_dooray.task.exception;

public class MilestoneNotFoundException extends RuntimeException {
    public MilestoneNotFoundException(){}
    public MilestoneNotFoundException(String msg){
        super(msg);
    }
}
