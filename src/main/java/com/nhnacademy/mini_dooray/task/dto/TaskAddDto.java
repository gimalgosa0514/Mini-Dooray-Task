package com.nhnacademy.mini_dooray.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskAddDto {
    private String memberId;
    private String taskTitle;
    private String taskContent;
}
