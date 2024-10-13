package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.task.entity.Milestone;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.exception.MilestoneNotFoundException;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.repository.MilestoneRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MilestoneServiceTest {

    @Mock
    private MilestoneRepository milestoneRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private MilestoneService milestoneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMilestonesByProjectId_Success() {
        Long projectId = 1L;
        Project project = new Project();
        Milestone milestone = new Milestone(1L, "Kickoff",
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59), project);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(milestoneRepository.findAllByProject_ProjectId(projectId)).thenReturn(List.of(milestone));

        List<MilestoneDto> result = milestoneService.getMilestonesByProjectId(projectId);

        assertEquals(1, result.size());
        assertEquals("Kickoff", result.get(0).getMilestoneName());
    }

    @Test
    void getMilestonesByProjectId_ProjectNotFound() {
        Long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProjectFoundException.class,
                () -> milestoneService.getMilestonesByProjectId(projectId));
    }

    @Test
    void addMilestoneToProject_Success() {
        Long projectId = 1L;
        Project project = new Project();
        MilestoneDto milestoneDto = new MilestoneDto(
                "New Milestone",
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59)
        );

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        MilestoneDto result = milestoneService.addMilestoneToProject(projectId, milestoneDto);

        assertEquals("New Milestone", result.getMilestoneName());
    }

    @Test
    void addMilestoneToProject_ProjectNotFound() {
        Long projectId = 1L;
        MilestoneDto milestoneDto = new MilestoneDto("New Milestone",
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59));

        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProjectFoundException.class,
                () -> milestoneService.addMilestoneToProject(projectId, milestoneDto));
    }

    @Test
    void updateMilestoneInProject_Success() {
        Long milestoneId = 1L;
        Milestone existingMilestone = new Milestone(milestoneId, "Old Milestone",
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59), new Project());

        MilestoneDto updateDto = new MilestoneDto(
                "Updated Milestone",
                LocalDateTime.of(2024, 11, 1, 0, 0),
                LocalDateTime.of(2025, 1, 31, 23, 59)
        );

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.of(existingMilestone));

        MilestoneDto result = milestoneService.updateMilestoneInProject(milestoneId, updateDto);

        assertEquals("Updated Milestone", result.getMilestoneName());
        assertEquals(LocalDateTime.of(2024, 11, 1, 0, 0), result.getMilestoneStartline());
    }

    @Test
    void updateMilestoneInProject_MilestoneNotFound() {
        Long milestoneId = 1L;
        MilestoneDto updateDto = new MilestoneDto(
                "Updated Milestone",
                LocalDateTime.of(2024, 11, 1, 0, 0),
                LocalDateTime.of(2025, 1, 31, 23, 59)
        );

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.empty());

        assertThrows(MilestoneNotFoundException.class,
                () -> milestoneService.updateMilestoneInProject(milestoneId, updateDto));
    }

    @Test
    void deleteProjectMilestone_Success() {
        Long milestoneId = 1L;
        when(milestoneRepository.existsById(milestoneId)).thenReturn(true);

        milestoneService.deleteProjectMilestone(milestoneId);

        verify(milestoneRepository, times(1)).deleteById(milestoneId);
    }

    @Test
    void deleteProjectMilestone_MilestoneNotFound() {
        Long milestoneId = 1L;
        when(milestoneRepository.existsById(milestoneId)).thenReturn(false);

        assertThrows(MilestoneNotFoundException.class,
                () -> milestoneService.deleteProjectMilestone(milestoneId));
    }
}
