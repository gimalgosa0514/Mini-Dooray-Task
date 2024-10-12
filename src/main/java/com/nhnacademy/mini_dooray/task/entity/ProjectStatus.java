package com.nhnacademy.mini_dooray.task.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nhnacademy.mini_dooray.task.exception.InvalidProjectStatusException;

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

    @JsonCreator
    public static ProjectStatus fromValue(String value) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new InvalidProjectStatusException("invalid project status");
    }
}
