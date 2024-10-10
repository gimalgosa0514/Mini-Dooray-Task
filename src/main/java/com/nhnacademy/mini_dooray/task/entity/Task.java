package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Task {
    @Id
    private String taskId;

    private String taskTitle;
    private String taskContent;
}
