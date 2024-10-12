package com.nhnacademy.mini_dooray.task.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue
    private Long taskId;

    private String taskTitle;
    private String taskContent;

    private String memberId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    public Task(Project project,String memberId,String taskTitle,String taskContent){
        this.project=project;
        this.memberId=memberId;
        this.taskTitle=taskTitle;
        this.taskContent=taskContent;
    }
}
