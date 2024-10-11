package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.Milestone;
import com.nhnacademy.mini_dooray.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
