package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import lombok.Getter;

@Getter
public class ProjectUpdateRequest {

    private String projectName;
    private ProjectStatus projectStatus;
}
