package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.dto.*;
import com.nhnacademy.mini_dooray.task.entity.Milestone;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.Task;
import com.nhnacademy.mini_dooray.task.exception.MilestoneNotFoundException;
import com.nhnacademy.mini_dooray.task.exception.TaskNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.MilestoneRepository;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;

    // 업무 목록 조회
    public List<TaskDto> getTaskList(long projectId){
        List<TaskDto> list=new ArrayList<>();

        for(Task task:taskRepository.findByProject_ProjectId(projectId)){
            TaskDto dto = new TaskDto(task.getTaskId(),task.getMemberId(),task.getTaskTitle(),task.getTaskContent());
            if(task.getMilestone()!=null){
                dto.setMilestoneName(task.getMilestone().getMilestoneName());
                dto.setMilestoneStartline(task.getMilestone().getMilestoneStartline());
                dto.setMilestoneDeadline(task.getMilestone().getMilestoneDeadline());
            }
            list.add(dto);
        }

        return list;
    }

    // 업무 상세 조회
    public TaskDto getTask(long id){
        Optional<Task> optional=taskRepository.findById(id);
        if(optional.isEmpty()){
            throw new TaskNotFoundException();
        }

        Task task=optional.get();
        TaskDto dto=new TaskDto(task.getTaskId(),task.getMemberId(),task.getTaskTitle(),task.getTaskContent());
        if(task.getMilestone()!=null){
            dto.setMilestoneName(task.getMilestone().getMilestoneName());
            dto.setMilestoneStartline(task.getMilestone().getMilestoneStartline());
            dto.setMilestoneDeadline(task.getMilestone().getMilestoneDeadline());
        }
        return dto;
    }

    // 업무 등록
    public TaskDto addTask(long projectId,TaskAddDto dto){
        Optional<Project> optional=projectRepository.findById(projectId);
        if(optional.isEmpty()){
            throw new RuntimeException();
        }

        Task task=taskRepository.save(new Task(optional.get(),dto.getMemberId(),dto.getTaskTitle(),dto.getTaskContent()));
        TaskDto taskDto=new TaskDto(task.getTaskId(),task.getMemberId(),task.getTaskTitle(),task.getTaskContent());
        if(task.getMilestone()!=null){
            taskDto.setMilestoneName(task.getMilestone().getMilestoneName());
            taskDto.setMilestoneStartline(task.getMilestone().getMilestoneStartline());
            taskDto.setMilestoneDeadline(task.getMilestone().getMilestoneDeadline());
        }

        return taskDto;
    }

    // 업무 수정
    public TaskDto editTask(long taskId,TaskEditDto dto){
        Optional<Task> optional=taskRepository.findById(taskId);
        if(optional.isEmpty()){
            throw new TaskNotFoundException();
        }

        Task oldTask=optional.get();
        Task newTask=taskRepository.save(new Task(taskId,dto.getTaskTitle(),dto.getTaskContent(),oldTask.getMemberId(),oldTask.getProject(),oldTask.getMilestone()));
        TaskDto taskDto=new TaskDto(newTask.getTaskId(),newTask.getMemberId(),newTask.getTaskTitle(),newTask.getTaskContent());
        if(newTask.getMilestone()!=null){
            taskDto.setMilestoneName(newTask.getMilestone().getMilestoneName());
            taskDto.setMilestoneStartline(newTask.getMilestone().getMilestoneStartline());
            taskDto.setMilestoneDeadline(newTask.getMilestone().getMilestoneDeadline());
        }

        return taskDto;
    }

    // 업무 삭제
    public void deleteTask(long taskId){
        taskRepository.deleteById(taskId);
    }

    // 진행 상황 등록
    public void addTaskMilestone(long taskId,TaskMilestoneDto dto){
        Optional<Task> optionalTask=taskRepository.findById(taskId);
        if(optionalTask.isEmpty()){
            throw new TaskNotFoundException();
        }
        Optional<Milestone> optionalMilestone=milestoneRepository.findById(dto.getMilestoneId());
        if(optionalMilestone.isEmpty()){
            throw new MilestoneNotFoundException();
        }

        Task task=optionalTask.get();
        taskRepository.save(new Task(task.getTaskId(),task.getTaskTitle(),task.getTaskContent(),task.getMemberId(),task.getProject(),optionalMilestone.get()));
    }

    // 진행 상황 삭제
    public void deleteTaskMilestone(long taskId){
        Optional<Task> optional=taskRepository.findById(taskId);
        if(optional.isEmpty()){
            throw new TaskNotFoundException();
        }

        Task task=optional.get();
        taskRepository.save(new Task(task.getTaskId(),task.getTaskTitle(),task.getTaskContent(),task.getMemberId(),task.getProject(),null));
    }
}
