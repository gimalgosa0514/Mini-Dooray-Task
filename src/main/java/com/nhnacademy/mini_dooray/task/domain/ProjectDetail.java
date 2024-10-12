package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProjectDetail {

    private Project project;
    private List<Member> members;
    private List<Task> tasks;
}
