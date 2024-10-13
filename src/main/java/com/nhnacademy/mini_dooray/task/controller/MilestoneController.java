package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.service.MilestoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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
    public ResponseEntity<ResponseMessage> addMilestoneToProject(@PathVariable Long projectId, @RequestBody MilestoneDto milestoneDto) {
        milestoneService.addMilestoneToProject(projectId, milestoneDto);
        ResponseMessage responseMessage = new ResponseMessage("등록 성공");
        return ResponseEntity
                .status(OK)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON) // JSON으로 응답
                .body(responseMessage);
//        return ResponseEntity.status(OK).body(responseMessage);
    }

    @PutMapping("/milestone/{milestoneId}")
    public ResponseEntity<ResponseMessage> updateMilestoneInProject(@PathVariable Long milestoneId, @RequestBody MilestoneDto milestoneDto) {
        milestoneService.updateMilestoneInProject(milestoneId, milestoneDto);
        ResponseMessage responseMessage = new ResponseMessage("수정 성공");
        return ResponseEntity.status(OK).body(responseMessage);
    }

    @DeleteMapping("/milestone/{milestoneId}")
    public ResponseEntity<ResponseMessage> deleteProjectMilestone(@PathVariable Long milestoneId) {
        milestoneService.deleteProjectMilestone(milestoneId);
        ResponseMessage responseMessage = new ResponseMessage("삭제 성공");
        return ResponseEntity.status(OK).body(responseMessage);
    }
}