package com.alc.todoapp.mapper;

import com.alc.todoapp.dto.TaskInDTO;
import com.alc.todoapp.persistence.entity.Task;
import com.alc.todoapp.persistence.entity.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOtoTask implements IMapper <TaskInDTO, Task> {

    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setFinished(false);

        return task;
    }
}
