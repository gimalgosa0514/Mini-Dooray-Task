package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
