package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long projectId;

    @Setter
    private String projectName;

    @Setter
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    private String projectManagerId;

    public Project(String projectName, ProjectStatus projectStatus, String projectManagerId) {
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.projectManagerId = projectManagerId;
    }
}
