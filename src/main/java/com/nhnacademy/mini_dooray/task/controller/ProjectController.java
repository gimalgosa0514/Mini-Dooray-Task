package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.*;
import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.exception.NoProjectFoundByMemberException;
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
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * 프로젝트 생성
     */
    @PostMapping
    public ResponseEntity<ResponseMessage> createProject(@RequestBody ProjectCreateRequest request) {
        projectService.createProject(request.getProjectName(), request.getMemberId());

        ResponseMessage responseMessage = new ResponseMessage("생성 성공");

        return ResponseEntity.status(CREATED).body(responseMessage);
    }

    /**
     * 프로젝트 Id로 프로젝트 상세 조회
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponse> getProjectDetailByProjectId(@PathVariable Long projectId) {

        ProjectDetailResponse projectDetailResponse = projectService.getProjectDetail(projectId);

        return ResponseEntity.status(OK).body(projectDetailResponse);
    }

    /**
     * 프로젝트 수정
     */
    @PutMapping("/{projectId}")
    public ResponseEntity<ResponseMessage> updateProject(
            @PathVariable Long projectId, @RequestBody ProjectUpdateRequest request) {
        projectService.updateProject(projectId, request);

        ResponseMessage responseMessage = new ResponseMessage("수정 성공");

        return ResponseEntity.status(OK).body(responseMessage);
    }

    /**
     * 프로젝트 삭제
     */
    @DeleteMapping("/{projectId}")
    public ResponseEntity<ResponseMessage> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);

        ResponseMessage responseMessage = new ResponseMessage("삭제 성공");

        return ResponseEntity.status(OK).body(responseMessage);
    }


}
