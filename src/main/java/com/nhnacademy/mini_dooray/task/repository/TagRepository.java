package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByProject_ProjectId(Long projectId);

    boolean existsByProject_ProjectId(Long projectId);

    boolean existsByTagNameAndProject(String tagName, Project project);
}
