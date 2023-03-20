package com.example.crud_app.service;

import com.example.crud_app.model.Task;
import com.example.crud_app.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final int PAGE_SIZE = 5;
    private final TaskRepository taskRepository;

    public TaskServiceImpl (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public List<Task> getTasks(Integer page, Sort.Direction sortDirection) {
        return taskRepository.findAllByTasks(PageRequest.of(page, PAGE_SIZE, Sort.by(sortDirection, "id")));
    }
    @Override
    public Task getSingleTask (long id) {
        return taskRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Task addTask (Task task) {
        return taskRepository.save(task);
    }
    @Transactional
    @Override
    public Task editTask (Task task) {
        Task taskEdited = taskRepository.findById(task.getId()).orElseThrow();
        taskEdited.setDescription(task.getDescription());
        taskEdited.setStatus(task.getStatus());
        return taskEdited;
    }

    @Override
    public void deleteTask (long id) {
        taskRepository.deleteById(id);
    }

}
