package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProjectDetailResponse {

    private String projectName;
    private ProjectStatus projectStatus;
    private String projectManagerId;
}
