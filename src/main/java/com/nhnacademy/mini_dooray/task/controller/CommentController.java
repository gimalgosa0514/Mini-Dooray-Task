package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.CommentDto;
import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/project/{projectId}/task/{taskId}/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getCommentsByTaskId(taskId));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addComment(@RequestBody CommentDto commentDto) {
        commentService.addComment(commentDto);
        ResponseMessage responseMessage = new ResponseMessage("생성 성공");
        return ResponseEntity.status(OK).body(responseMessage);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseMessage> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        commentService.updateComment(commentId, commentDto);
        ResponseMessage responseMessage = new ResponseMessage("수정 성공");
        return ResponseEntity.status(OK).body(responseMessage);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable Long commentId) {
        commentService.removeComment(commentId);
        ResponseMessage responseMessage = new ResponseMessage("삭제 성공");
        return ResponseEntity.status(OK).body(responseMessage);
    }
}