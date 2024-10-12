package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.domain.CommentDto;
import com.nhnacademy.mini_dooray.task.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentDto> findAllByTaskId(Long taskId);
}