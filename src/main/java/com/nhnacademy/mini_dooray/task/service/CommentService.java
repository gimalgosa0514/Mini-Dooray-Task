package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.CommentDto;
import com.nhnacademy.mini_dooray.task.entity.Comment;
import com.nhnacademy.mini_dooray.task.entity.Member;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.exception.CommentNotFoundException;
import com.nhnacademy.mini_dooray.task.exception.MemberNotFoundException;
import com.nhnacademy.mini_dooray.task.exception.TaskNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.CommentRepository;
import com.nhnacademy.mini_dooray.task.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.taskRepository = taskRepository;
    }

    public List<CommentDto> getCommentsByTaskId(Long taskId) {
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        return commentRepository.findAllByTaskId(taskId);
    }

    public CommentDto addComment(CommentDto commentDto) {

        Member member = memberRepository.findById(Long.valueOf((commentDto.getMemberId())))
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + commentDto.getMemberId()));

        Task task = taskRepository.findById(commentDto.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + commentDto.getTaskId()));

        Comment comment = Comment.builder()
                .commentContent(commentDto.getCommentContent())
                .member(member)
                .task(task)
                .build();

        comment = commentRepository.save(comment);
        return new CommentDto(comment.getCommentContent(), comment.getMember().getMemberId(), comment.getTask().getTaskId());
    }

    public CommentDto updateComment(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        comment.setCommentContent(commentDto.getCommentContent());
        commentRepository.save(comment);

        return new CommentDto(comment.getCommentContent(), comment.getMember().getMemberId(), comment.getTask().getTaskId());
    }

    public void removeComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }
}