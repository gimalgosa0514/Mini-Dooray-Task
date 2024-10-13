package com.nhnacademy.mini_dooray.task.service;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.nhnacademy.mini_dooray.task.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.task.domain.ProjectUpdateRequest;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectMemberRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project("Test Project", ACTIVE, "managerId");
    }

    @Test
    void createProject() {

        projectService.createProject("Test Project", "managerId");

        verify(projectRepository).save(any(Project.class));
        verify(projectMemberRepository).save(any(ProjectMember.class));
    }

    @Test
    void getProjectDetail() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectDetailResponse response = projectService.getProjectDetail(1L);

        assertThat(response.getProjectName()).isEqualTo("Test Project");
        assertThat(response.getProjectStatus()).isEqualTo(ACTIVE);
        assertThat(response.getProjectManagerId()).isEqualTo("managerId");
    }

    @Test
    void getProjectDetail_ProjectNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.getProjectDetail(1L))
                .isInstanceOf(NoSuchProjectFoundException.class)
                .hasMessage("project not found");
    }

    @Test
    void updateProject() {
        ProjectUpdateRequest request = new ProjectUpdateRequest("Updated Project", SUSPENDED);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        projectService.updateProject(1L, request);

        assertThat(project.getProjectName()).isEqualTo("Updated Project");
        assertThat(project.getProjectStatus()).isEqualTo(SUSPENDED);
    }

    @Test
    void updateProject_ProjectNotFound() {
        ProjectUpdateRequest request = new ProjectUpdateRequest("Updated Project", SUSPENDED);
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.updateProject(1L, request))
                .isInstanceOf(NoSuchProjectFoundException.class)
                .hasMessage("project not found");
    }

    @Test
    void deleteProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        projectService.deleteProject(1L);

        verify(projectRepository).delete(project);
    }

    @Test
    void deleteProject_ProjectNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.deleteProject(1L))
                .isInstanceOf(NoSuchProjectFoundException.class)
                .hasMessage("project not found");
    }
}
