package com.nhnacademy.mini_dooray.task.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneRequest {
    private String milestoneName;

    private LocalDateTime milestoneStartline;
    private LocalDateTime milestoneDeadline;
}
