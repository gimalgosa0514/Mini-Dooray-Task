package com.nhnacademy.mini_dooray.task.dto;

import com.nhnacademy.mini_dooray.task.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private long taskId;
    private String memberId;
    private String taskTitle;
    private String taskContent;
    @Setter
    private String milestoneName;
    @Setter
    private LocalDateTime milestoneStartline;
    @Setter
    private LocalDateTime milestoneDeadline;
    @Setter
    private List<Tag> tagName;

    public TaskDto(long taskId,String memberId,String taskTitle,String taskContent){
        this.taskId=taskId;
        this.memberId=memberId;
        this.taskTitle=taskTitle;
        this.taskContent=taskContent;
    }
}
