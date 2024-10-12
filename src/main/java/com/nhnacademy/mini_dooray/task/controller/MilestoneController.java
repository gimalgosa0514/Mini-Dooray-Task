package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.task.service.MilestoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/project/{projectId}")
public class MilestoneController {
    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/milestone")
    public ResponseEntity<List<MilestoneDto>> getMilestonesByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(milestoneService.getMilestonesByProjectId(projectId));
    }

    @PostMapping("/milestone")
    public ResponseEntity<MilestoneDto> addMilestoneToProject(@PathVariable Long projectId, @RequestBody MilestoneDto milestoneDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(milestoneService.addMilestoneToProject(projectId, milestoneDto));
    }

    @PutMapping("/milestone/{milestoneId}")
    public ResponseEntity<MilestoneDto> updateMilestoneInProject(@PathVariable Long milestoneId, @RequestBody MilestoneDto milestoneDto) {
        return ResponseEntity.ok(milestoneService.updateMilestoneInProject(milestoneId, milestoneDto));
    }

    @DeleteMapping("/milestone/{milestoneId}")
    public ResponseEntity<Void> deleteProjectMilestone(@PathVariable Long milestoneId) {
        milestoneService.deleteProjectMilestone(milestoneId);
        return ResponseEntity.noContent().build();
    }
}