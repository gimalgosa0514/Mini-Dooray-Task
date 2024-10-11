package com.nhnacademy.mini_dooray.task.domain;

import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String commentContent;
    private String memberId;
    private long taskId;
}