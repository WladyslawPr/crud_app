package com.example.crud_app.service;

import com.example.crud_app.model.Task;
import com.example.crud_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getSingleTask (Long id) {
        return taskRepository.findById(id)
                .orElseThrow();
    }
}
