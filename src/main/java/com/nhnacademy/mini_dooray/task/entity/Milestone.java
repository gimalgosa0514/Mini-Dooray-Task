package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {

    @Id
    @GeneratedValue
    private Long milestoneId;

    private String milestoneName;
    private LocalDateTime milestoneStartline;
    private LocalDateTime milestoneDeadline;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}