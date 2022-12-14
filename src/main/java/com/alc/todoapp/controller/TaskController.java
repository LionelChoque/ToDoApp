package com.alc.todoapp.controller;

import com.alc.todoapp.dto.TaskInDTO;
import com.alc.todoapp.persistence.entity.Task;
import com.alc.todoapp.persistence.entity.TaskStatus;
import com.alc.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO){
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> getAll(){ return this.taskService.getAll();  }
    
    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus(@PathVariable("status")TaskStatus status){
        return this.taskService.finAllByTaskStatus(status);
    }

    @PatchMapping("/update_satus/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
