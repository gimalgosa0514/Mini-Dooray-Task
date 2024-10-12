package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.task.domain.ProjectDetail;
import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final TaskRepository taskRepository;

    public void saveProject(ProjectCreateRequest request) {
        Project project = new Project(request.getProjectName(), ACTIVE, request.getMemberId());
        projectRepository.save(project);
    }

    public ProjectDetail getProjectDetailById(Long projectId) {
        Project foundProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("project not found"));

        List<ProjectMember> projectMembers = projectMemberRepository.findByProject_ProjectId(projectId);
        List<Member> members = projectMembers.stream()
                .map(projectMember -> projectMember.getMember())
                .collect(Collectors.toList());

        List<Task> tasks = taskRepository.findByProject_ProjectId(projectId);

        return new ProjectDetail(foundProject, members, tasks);
    }
}
