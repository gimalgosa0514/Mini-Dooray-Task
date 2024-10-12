package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.task.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import com.nhnacademy.mini_dooray.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectMemberRepository projectMemberRepository;

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
    public ResponseEntity<ProjectDetailResponse> getProjectByProjectId(@PathVariable Long projectId) {
        Project foundProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("project not found"));

        List<ProjectMember> projectMembers = projectMemberRepository.findByProject_ProjectId(projectId);
        List<Member> members = projectMembers.stream()
                .map(projectMember -> projectMember.getMember())
                .collect(Collectors.toList());


        ProjectDetailResponse responseProject = new ProjectDetailResponse(
                foundProject.getProjectName(), foundProject.getProjectStatus(), foundProject.getProjectManagerId(),
                null,null);

        return ResponseEntity.status(OK).body(responseProject);
    }
}
