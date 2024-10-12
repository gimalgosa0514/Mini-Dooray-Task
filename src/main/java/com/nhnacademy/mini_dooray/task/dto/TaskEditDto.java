package com.nhnacademy.mini_dooray.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEditDto {
    private String taskTitle;
    private String taskContent;
}
