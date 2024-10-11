package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjectDetailResponse {

    private String projectName;
    private ProjectStatus projectStatus;
    private String projectManagerId;
}
