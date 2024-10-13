package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long commentId;

    private String commentContent;


    private String memberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
}
