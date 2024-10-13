package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.CommentDto;
import com.nhnacademy.mini_dooray.task.entity.Comment;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.exception.CommentNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.CommentRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCommentsByTaskId_Success() {
        Task task = Task.builder()
                .taskId(1L)
                .taskTitle("Test Task")
                .taskContent("Test Content")
                .memberId("A123")
                .build();

        Comment comment = Comment.builder()
                .commentContent("Test comment")
                .memberId("A123")
                .task(task)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(commentRepository.findAllByTask_TaskId(1L)).thenReturn(List.of(comment));

        List<CommentDto> result = commentService.getCommentsByTaskId(1L);

        assertEquals(1, result.size());
        assertEquals("Test comment", result.get(0).getCommentContent());
    }

    @Test
    void addComment_Success() {
        Task task = Task.builder()
                .taskId(1L)
                .taskTitle("Test Task")
                .taskContent("Test Content")
                .memberId("A123")
                .build();

        CommentDto commentDto = new CommentDto("Test comment", "A123", 1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CommentDto result = commentService.addComment(commentDto);

        assertNotNull(result);
        assertEquals("Test comment", result.getCommentContent());
        assertEquals("A123", result.getMemberId());
        assertEquals(1L, result.getTaskId());
    }

    @Test
    void updateComment_Success() {
        Task task = Task.builder()
                .taskId(1L)
                .taskTitle("Test Task")
                .taskContent("Test Content")
                .memberId("A123")
                .build();

        Comment existingComment = Comment.builder()
                .commentId(1L)
                .commentContent("Old content")
                .memberId("A123")
                .task(task)
                .build();

        CommentDto updateDto = new CommentDto("Updated comment", "A123", 1L);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(existingComment));

        CommentDto result = commentService.updateComment(1L, updateDto);

        assertEquals("Updated comment", result.getCommentContent());
    }

    @Test
    void updateComment_CommentNotFound() {
        Long commentId = 2L;
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        CommentDto updateDto = new CommentDto("Updated comment", "A123", 1L);

        CommentNotFoundException exception = assertThrows(
                CommentNotFoundException.class,
                () -> commentService.updateComment(commentId, updateDto)
        );

        assertEquals("Comment not found with id: 2", exception.getMessage());
    }

    @Test
    void removeComment_Success() {
        Long commentId = 1L;

        when(commentRepository.existsById(commentId)).thenReturn(true);

        commentService.removeComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
    }

    @Test
    void removeComment_ThrowsCommentNotFoundException() {
        Long commentId = 2L;
        when(commentRepository.existsById(commentId)).thenReturn(false);

        CommentNotFoundException exception = assertThrows(
                CommentNotFoundException.class,
                () -> commentService.removeComment(commentId)
        );

        assertEquals("Comment not found with id: 2", exception.getMessage());
    }
}
