package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Project {
    @Id
    private String projectId;

    private String projectName;
    private String projectStatus;
    private String projectManagerId;
}
