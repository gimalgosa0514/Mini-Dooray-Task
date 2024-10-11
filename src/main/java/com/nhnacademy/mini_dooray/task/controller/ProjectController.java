package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.ProjectDto;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.*;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto) {
        Project project = new Project(projectDto.getTitle(), ACTIVE, projectDto.getMemberId());
        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }
}
