package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.dto.TaskAddDto;
import com.nhnacademy.mini_dooray.task.dto.TaskDto;
import com.nhnacademy.mini_dooray.task.dto.TaskEditDto;
import com.nhnacademy.mini_dooray.task.dto.TaskMilestoneDto;
import com.nhnacademy.mini_dooray.task.entity.Milestone;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.exception.TaskNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.MilestoneRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private MilestoneRepository milestoneRepository;

    @InjectMocks
    private TaskService taskService;

    private Project project;

    @BeforeEach
    void setup(){
        project=new Project(1L,"project name", ProjectStatus.ACTIVE,"test");
    }

    @Test
    void addTask(){
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task=invocation.getArgument(0);
            return new Task(1L,task.getTaskTitle(),task.getTaskContent(),task.getMemberId(),task.getProject(),null);
        });
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        TaskDto task=taskService.addTask(1L,new TaskAddDto("test","test title","test content"));

        verify(taskRepository).save(any(Task.class));
        assertNotNull(task);
        assertEquals("test",task.getMemberId());
        assertEquals("test title",task.getTaskTitle());
    }

    @Test
    void getTask(){
        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L,"test1","test1111","test",project,null)));
        Task milestoneTask=new Task(2L,"test1","test1111","test",project,new Milestone(1L,"milestone test", LocalDateTime.now(),LocalDateTime.now(),project));
        when(taskRepository.findById(2L)).thenReturn(Optional.of(milestoneTask));

        TaskDto dto1=taskService.getTask(1L);
        assertEquals("test",dto1.getMemberId());
        assertEquals("test1",dto1.getTaskTitle());
        assertEquals("test1111",dto1.getTaskContent());

        TaskDto dto2=taskService.getTask(2L);
        assertEquals("milestone test",dto2.getMilestoneName());
    }

    @Test
    void taskNotFoundException(){
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,()->{
            taskService.getTask(1L);
        });
    }

    @Test
    void getTaskList(){
        List<Task> list=new ArrayList<>();
        list.add(new Task(1L,"task1","content1","asdf",project,null));
        list.add(new Task(2L,"task22","content22","test",project,null));
        list.add(new Task(3L,"task333","content333","asdf",project,null));

        when(taskRepository.findByProject_ProjectId(1L)).thenReturn(list);

        List<TaskDto> listDto=taskService.getTaskList(1L);
        assertEquals("asdf",listDto.get(0).getMemberId());
        assertEquals("task22",listDto.get(1).getTaskTitle());
    }

    @Test
    void editTask(){
//        Project project=new Project(1L,"project name", ProjectStatus.ACTIVE,"test");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L,"test1","test1111","test",project,new Milestone(1L,"milestone test", LocalDateTime.now(),LocalDateTime.now(),project))));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task=invocation.getArgument(0);
            return new Task(task.getTaskId(),task.getTaskTitle(),task.getTaskContent(),task.getMemberId(),task.getProject(),task.getMilestone());
        });
        TaskDto dto=taskService.editTask(1L,new TaskEditDto("수정","내용"));
        assertEquals(1L,dto.getTaskId());
        assertEquals("수정",dto.getTaskTitle());
        assertEquals("내용",dto.getTaskContent());
    }

    @Test
    void deleteTask(){
        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);
    }

    @Test
    void addTaskMilestone(){
        LocalDateTime time=LocalDateTime.now();
        when(milestoneRepository.findById(1L)).thenReturn(Optional.of(new Milestone(1L,"milestone test", time,time,project)));
        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L,"test1","test1111","test",project,null)));
        TaskDto dto=taskService.getTask(1L);
        assertNull(dto.getMilestoneName());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L,"test1","test1111","test",project,new Milestone(1L,"milestone test",time,time,project))));
        taskService.addTaskMilestone(1L,new TaskMilestoneDto(1));
        dto=taskService.getTask(1L);
        assertEquals(time,dto.getMilestoneStartline());
    }

    @Test
    void deleteTaskMilestone(){
//        Project project=new Project(1L,"project name", ProjectStatus.ACTIVE,"test");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L,"test1","test1111","test",project,new Milestone(1L,"milestone test", LocalDateTime.now(),LocalDateTime.now(),project))));
        taskService.deleteTaskMilestone(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task(1L,"test1","test1111","test",project,null)));
        TaskDto dto=taskService.getTask(1L);
        assertNull(dto.getMilestoneName());
        assertNull(dto.getMilestoneStartline());
        assertNull(dto.getMilestoneDeadline());
    }
}
