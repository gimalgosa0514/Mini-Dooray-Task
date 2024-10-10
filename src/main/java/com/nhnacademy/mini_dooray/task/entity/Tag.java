package com.nhnacademy.mini_dooray.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id @GeneratedValue
    private Long tagId;

    private String tagName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
}
