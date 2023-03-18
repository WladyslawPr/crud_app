package com.example.crud_app.service;

import com.example.crud_app.model.Task;
import com.example.crud_app.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final int PAGE_SIZE = 5;
    private final TaskRepository taskRepository;

    public TaskServiceImpl (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(Integer page, Sort.Direction sortDirection) {
        return taskRepository.findAllByTasks(PageRequest.of(page, PAGE_SIZE, Sort.by(sortDirection, "id")));
    }

    public Task getSingleTask (Long id) {
        return taskRepository.findById(id)
                .orElseThrow();
    }
}
