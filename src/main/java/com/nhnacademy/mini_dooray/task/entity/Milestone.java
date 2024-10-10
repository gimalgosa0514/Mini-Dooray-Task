package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Milestone {
    @Id
    private String milestoneId;
    private String milestoneName;
    private LocalDateTime milestoneStartline;
    private LocalDateTime milestoneDeadline;
}