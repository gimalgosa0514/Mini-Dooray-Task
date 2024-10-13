package com.nhnacademy.mini_dooray.task.service;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoProjectFoundByMemberException;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProjectMemberServiceTest {

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectMemberService projectMemberService;

    private ProjectMember projectMember;

    @BeforeEach
    void setUp() {
        projectMember = new ProjectMember("aaa", new Project("Test Project", ACTIVE, "managerId"));
    }

    @Test
    void getProjectMembers() {
        String memberId = "aaa";

        when(projectMemberRepository.existsByMemberId(memberId)).thenReturn(true);
        when(projectMemberRepository.findByMemberId(memberId)).thenReturn(Collections.singletonList(projectMember));

        List<ProjectMember> projectMembers = projectMemberService.getProjectMembers(memberId);

        assertThat(projectMembers).hasSize(1);
        assertThat(projectMembers.getFirst().getMemberId()).isEqualTo("aaa");
    }

    @Test
    void getProjectMembers_NoProjects() {
        String memberId = "aaa";

        when(projectMemberRepository.existsByMemberId(memberId)).thenReturn(false);

        assertThatThrownBy(() -> projectMemberService.getProjectMembers(memberId))
                .isInstanceOf(NoProjectFoundByMemberException.class)
                .hasMessage("this member has no project");
    }

    @Test
    void saveProjectMember() {
        Long projectId = 1L;
        String memberId = "aaa";
        Project foundProject = new Project("Test Project", ACTIVE, "managerId");

        when(projectRepository.existsById(projectId)).thenReturn(true);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(foundProject));

        projectMemberService.saveProjectMember(projectId, memberId);

        verify(projectMemberRepository).save(any(ProjectMember.class));
    }

    @Test
    void saveProjectMember_ProjectNotFound() {
        Long projectId = 1L;
        String memberId = "aaa";

        when(projectRepository.existsById(projectId)).thenReturn(false);

        assertThatThrownBy(() -> projectMemberService.saveProjectMember(projectId, memberId))
                .isInstanceOf(NoSuchProjectFoundException.class)
                .hasMessage("project not found");
    }
}
