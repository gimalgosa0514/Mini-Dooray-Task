package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
