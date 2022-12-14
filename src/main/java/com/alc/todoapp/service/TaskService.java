package com.alc.todoapp.service;

import com.alc.todoapp.dto.TaskInDTO;
import com.alc.todoapp.exceptions.ToDoExceptions;
import com.alc.todoapp.mapper.TaskInDTOtoTask;
import com.alc.todoapp.persistence.entity.Task;
import com.alc.todoapp.persistence.entity.TaskStatus;
import com.alc.todoapp.persistence.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskInDTOtoTask taskInDTOtoTask;

    public TaskService(TaskRepository taskRepository, TaskInDTOtoTask taskInDTOtoTask) {
        this.taskRepository = taskRepository;
        this.taskInDTOtoTask = taskInDTOtoTask;
    }

    public Task createTask(TaskInDTO taskInDTO){
        Task task = taskInDTOtoTask.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> getAll(){
        return this.taskRepository.findAll();
    }

    public List<Task> finAllByTaskStatus(TaskStatus taskStatus){
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if(taskOptional.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }
    public void deleteTask(Long id){
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if(taskOptional.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}
