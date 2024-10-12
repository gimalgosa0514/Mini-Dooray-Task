package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.dto.*;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/project/{projectId}/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // 업무 목록 조회
    @GetMapping
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable long projectId){
        return new ResponseEntity<>(taskService.getTaskList(projectId),HttpStatus.OK);
    }

    // 업무 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable long id,HttpServletRequest request){
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    // 업무 등록
    @PostMapping
    public ResponseEntity<ResponseMessage> addTask(@PathVariable long projectId, @RequestBody TaskAddDto dto){
        taskService.addTask(projectId,dto);
        return new ResponseEntity<>(new ResponseMessage("생성 성공"),HttpStatus.OK);
    }

    // 업무 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> editTask(@PathVariable long id,@RequestBody TaskEditDto dto){
        taskService.editTask(id,dto);
        return new ResponseEntity<>(new ResponseMessage("수정 성공"),HttpStatus.OK);
    }

    // 업무 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteTask(@PathVariable long id){
        taskService.deleteTask(id);

        return new ResponseEntity<>(new ResponseMessage("삭제 성공"),HttpStatus.OK);
    }
}
