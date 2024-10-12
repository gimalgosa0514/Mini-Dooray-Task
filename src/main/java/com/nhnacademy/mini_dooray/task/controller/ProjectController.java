package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.ProjectDetail;
import com.nhnacademy.mini_dooray.task.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.task.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * 프로젝트 생성
     */
    @PostMapping
    public ResponseEntity<ResponseMessage> createProject(@RequestBody ProjectCreateRequest request) {
        projectService.saveProject(request);

        ResponseMessage responseMessage = new ResponseMessage("생성 성공");

        return ResponseEntity.status(CREATED).body(responseMessage);
    }

    /**
     * 프로젝트 Id로 프로젝트 상세 조회
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponse> getProjectDetailByProjectId(@PathVariable Long projectId) {
        ProjectDetail projectDetail = projectService.getProjectDetailById(projectId);
        Project project = projectDetail.getProject();
        List<String> memberIds = projectDetail.getMembers().stream()
                .map(member -> member.getMemberId())
                .collect(Collectors.toList());

        ProjectDetailResponse projectDetailResponse = new ProjectDetailResponse(
                project.getProjectName(), project.getProjectStatus(), project.getProjectManagerId(),
                projectDetail.getTasks(), memberIds);

        return ResponseEntity.status(OK).body(projectDetailResponse);
    }
}
