package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectListResponse {

    private Long projectId;
    private String projectName;
    private ProjectStatus projectStatus;
    private String projectManagerId;
}
