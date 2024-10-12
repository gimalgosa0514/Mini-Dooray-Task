package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.ProjectListResponse;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.domain.projectMemberRequest;
import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;

    /**
     * memeber Id로 해당 멤버가 속한 프로젝트 목록 조회
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ProjectListResponse>> getProjectsByMemberId(@PathVariable String memberId) {
        List<ProjectMember> projectMembers = projectMemberService.getProjectMembersByMemberId(memberId);
        List<ProjectListResponse> projects = projectMembers.stream()
                .map(projectMember -> new ProjectListResponse(projectMember.getProject().getProjectName(), projectMember.getProject().getProjectStatus()))
                .collect(Collectors.toList());

        return ResponseEntity.status(OK).body(projects);
    }

    /**
     * project Id에 해당하는 프로젝트에 멤버 등록
     */
    @PostMapping("/{projectId}/member")
    public ResponseEntity<ResponseMessage> createProjectMember(
            @PathVariable Long projectId, @RequestBody projectMemberRequest request) {
        if (projectRepository.existsById(projectId)) {
            Project foundProject = projectRepository.findById(projectId).get();
            Member requestMember = memberRepository.findById(request.getMemberId()).get();
            projectMemberRepository.save(new ProjectMember(requestMember, foundProject));

            ResponseMessage responseMessage = new ResponseMessage("등록 성공");
            return ResponseEntity.status(OK).body(responseMessage);
        } else {
            throw new NoSuchProjectFoundException("project not found");
        }
    }
}