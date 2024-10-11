package com.nhnacademy.mini_dooray.task.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectStatus {
    ACTIVE("활성"), SUSPENDED("휴면"), TERMINATED("종료");

    private String statusName;

    ProjectStatus(String statusName) {
        this.statusName = statusName;
    }

    @JsonValue
    public String toJson(){
        return this.statusName;
    }
}
