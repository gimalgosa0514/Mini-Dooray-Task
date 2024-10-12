package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    private String milestoneName;
    private LocalDateTime milestoneStartline;
    private LocalDateTime milestoneDeadline;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}