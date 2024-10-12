package com.nhnacademy.mini_dooray.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TaskListDto {
    private List<TaskDto> tasks;
}
