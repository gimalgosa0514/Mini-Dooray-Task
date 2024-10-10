package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProjectMember {
    @Id
    private String projectMemberId;
}
