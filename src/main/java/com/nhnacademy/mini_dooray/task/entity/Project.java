package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id @GeneratedValue
    private Long projectId;

    private String projectName;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private String projectManagerId;

    public Project(String projectName, ProjectStatus projectStatus, String projectManagerId) {
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.projectManagerId = projectManagerId;
    }
}
