package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoProjectFoundByMemberException;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<ProjectMember> getProjectMembers(String memberId) {
        if (projectMemberRepository.existsByMemberId(memberId)) {
            return projectMemberRepository.findByMemberId(memberId);
        } else {
            throw new NoProjectFoundByMemberException("this member has no project");
        }
    }

    public void saveProjectMember(Long projectId, String memberId) {
        if (projectRepository.existsById(projectId)) {
            Project foundProject = projectRepository.findById(projectId).get();
            projectMemberRepository.save(new ProjectMember(memberId, foundProject));
        } else {
            throw new NoSuchProjectFoundException("project not found");
        }
    }

    public List<ProjectMember> getMembersInProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new NoSuchProjectFoundException("project not found");
        }
        return projectMemberRepository.findByProject_ProjectId(projectId);
    }
}
