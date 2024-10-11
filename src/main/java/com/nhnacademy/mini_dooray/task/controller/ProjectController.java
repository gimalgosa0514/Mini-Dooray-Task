package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.ProjectListResponse;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.task.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<ResponseMessage> createProject(@RequestBody ProjectCreateRequest request) {
        Project project = new Project(request.getTitle(), ACTIVE, request.getMemberId());
        projectRepository.save(project);

        ResponseMessage responseMessage = new ResponseMessage("생성 성공");

        return ResponseEntity.status(CREATED).body(responseMessage);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponse> getProjectByProjectId(@PathVariable Long projectId) {
        Project foundProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("project not found"));

        ProjectDetailResponse responseProject = new ProjectDetailResponse(
                foundProject.getProjectName(), foundProject.getProjectStatus(), foundProject.getProjectManagerId());

        return ResponseEntity.status(OK).body(responseProject);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<ProjectListResponse>> getProjectsByMemberId(@PathVariable String memberId) {
        List<Project> projects = projectRepository.findByProjectManagerId(memberId);

        List<ProjectListResponse> projectLists = projects.stream()
                .map(project -> new ProjectListResponse(project.getProjectName(), project.getProjectStatus()))
                .collect(Collectors.toList());

        return ResponseEntity.status(OK).body(projectLists);
    }

    
}
