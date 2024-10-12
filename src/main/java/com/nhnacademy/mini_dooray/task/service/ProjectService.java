package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.task.domain.ProjectUpdateRequest;
import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    private final MemberRepository memberRepository;

    public void createProject(String projectName, String memberId) {
        Project newProject = new Project(projectName, ACTIVE, memberId);
        projectRepository.save(newProject);
        projectMemberRepository.save(new ProjectMember(memberId, newProject));
    }

    public ProjectDetailResponse getProjectDetail(Long projectId) {
        Project foundProject = getProjectById(projectId);

        return new ProjectDetailResponse(
                foundProject.getProjectName(), foundProject.getProjectStatus(), foundProject.getProjectManagerId());
    }

    public void updateProject(Long projectId, ProjectUpdateRequest request) {
        Project foundProject = getProjectById(projectId);

        if (request.getProjectName() != null) {
            foundProject.setProjectName(request.getProjectName());
        }
        if (request.getProjectStatus() != null) {
            foundProject.setProjectStatus(request.getProjectStatus());
        }
    }

    public void deleteProject(Long projectId) {
        Project foundProject = getProjectById(projectId);
        projectRepository.delete(foundProject);
    }

    private Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("project not found"));
    }
}
