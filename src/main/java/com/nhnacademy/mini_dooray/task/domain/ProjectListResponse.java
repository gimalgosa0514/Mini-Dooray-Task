package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjectListResponse {

    private String projectName;
    private ProjectStatus projectStatus;
}
