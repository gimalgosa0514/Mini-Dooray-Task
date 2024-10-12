package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoProjectFoundByMemberException;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public List<ProjectMember> getProjectMembersByMemberId(String memberId) {
        if (projectMemberRepository.existsByMember_MemberId(memberId)) {
            return projectMemberRepository.findByMember_MemberId(memberId);
        } else {
            throw new NoProjectFoundByMemberException("this member has no project");
        }
    }

    public void saveProjectMember(Long projectId, String memberId) {
        if (projectRepository.existsById(projectId)) {
            Project foundProject = projectRepository.findById(projectId).get();
            Member requestMember = memberRepository.findById(memberId).get();
            projectMemberRepository.save(new ProjectMember(requestMember, foundProject));
        } else {
            throw new NoSuchProjectFoundException("project not found");
        }
    }
}
