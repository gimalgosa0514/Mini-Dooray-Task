package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoProjectByMemberException;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMember> getProjectMembersByMemberId(String memberId) {
        if (projectMemberRepository.existsByMember_MemberId(memberId)) {
            return projectMemberRepository.findByMember_MemberId(memberId);
        } else {
            throw new NoProjectByMemberException("this member has no project");
        }

    }
}
