package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    private long commentId;

    private String commentContent;
}
