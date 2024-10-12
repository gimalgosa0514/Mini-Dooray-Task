package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.CommentDto;
import com.nhnacademy.mini_dooray.task.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(commentDto));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.removeComment(commentId);
        return ResponseEntity.noContent().build();
    }
}