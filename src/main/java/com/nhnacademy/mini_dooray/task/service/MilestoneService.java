package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.task.domain.MilestoneRequest;
import com.nhnacademy.mini_dooray.task.entity.Milestone;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.exception.*;
import com.nhnacademy.mini_dooray.task.repository.MilestoneRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneService(MilestoneRepository milestoneRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    public List<MilestoneDto> getMilestonesByProjectId(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(() -> new NoSuchProjectFoundException("Project not found with id: " + projectId));
        List<Milestone> milestones = milestoneRepository.findAllByProject_ProjectId(projectId);
        return milestones.stream()
                .map(milestone -> new MilestoneDto(
                        milestone.getMilestoneId(),
                        milestone.getMilestoneName(),
                        milestone.getMilestoneStartline(),
                        milestone.getMilestoneDeadline()
                ))
                .toList();
    }

    public MilestoneDto addMilestoneToProject(Long projectId, MilestoneRequest milestoneRequest) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("Project not found with id: " + projectId));

        Milestone milestone = Milestone.builder()
                .milestoneName(milestoneRequest.getMilestoneName())
                .milestoneStartline(milestoneRequest.getMilestoneStartline())
                .milestoneDeadline(milestoneRequest.getMilestoneDeadline())
                .project(project)
                .build();

        milestoneRepository.save(milestone);
        return new MilestoneDto(milestone.getMilestoneId(),milestone.getMilestoneName(), milestone.getMilestoneStartline(), milestone.getMilestoneDeadline());
    }

    public MilestoneDto updateMilestoneInProject(Long milestoneId, MilestoneDto milestoneDto) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found with id: " + milestoneId));

        milestone.setMilestoneName(milestoneDto.getMilestoneName());
        milestone.setMilestoneStartline(milestoneDto.getMilestoneStartline());
        milestone.setMilestoneDeadline(milestoneDto.getMilestoneDeadline());
        milestoneRepository.save(milestone);

        return new MilestoneDto(milestone.getMilestoneId(),milestone.getMilestoneName(), milestone.getMilestoneStartline(), milestone.getMilestoneDeadline());
    }

    public void deleteProjectMilestone(Long milestoneId) {
        if (!milestoneRepository.existsById(milestoneId)) {
            throw new MilestoneNotFoundException("Milestone not found with id: " + milestoneId);
        }
        milestoneRepository.deleteById(milestoneId);
    }
}
