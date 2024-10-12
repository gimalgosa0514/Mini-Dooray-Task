package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.task.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;

    /**
     * 프로젝트 생성
     */
    @PostMapping
    public ResponseEntity<ResponseMessage> createProject(@RequestBody ProjectCreateRequest request) {
        Project project = new Project(request.getProjectName(), ACTIVE, request.getMemberId());
        projectRepository.save(project);

        ResponseMessage responseMessage = new ResponseMessage("생성 성공");

        return ResponseEntity.status(CREATED).body(responseMessage);
    }

    /**
     * 프로젝트 Id로 프로젝트 상세 조회
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponse> getProjectByProjectId(@PathVariable Long projectId) {
        Project foundProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("project not found"));

        ProjectDetailResponse responseProject = new ProjectDetailResponse(
                foundProject.getProjectName(), foundProject.getProjectStatus(), foundProject.getProjectManagerId());

        return ResponseEntity.status(OK).body(responseProject);
    }
}
