package com.nhnacademy.mini_dooray.task.dto;

import com.nhnacademy.mini_dooray.task.domain.TagDto;
import com.nhnacademy.mini_dooray.task.entity.Tag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private long taskId;
    private String memberId;
    private String taskTitle;
    private String taskContent;

    private String milestoneName;

    private LocalDateTime milestoneStartline;

    private LocalDateTime milestoneDeadline;

    private List<TagDto> tagName;

    public TaskDto(long taskId,String memberId,String taskTitle,String taskContent){
        this.taskId=taskId;
        this.memberId=memberId;
        this.taskTitle=taskTitle;
        this.taskContent=taskContent;
    }
}
