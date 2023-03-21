package com.example.crud_app.controller.rest;

import com.example.crud_app.dto.TaskDTO;
import com.example.crud_app.mapper.TaskDTOMapper;
import com.example.crud_app.model.Task;
import com.example.crud_app.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(@RequestParam(required = false) Integer page, Sort.Direction sort,
                                  @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return TaskDTOMapper.mapToTaskDTOs(taskService.getTasks(pageNumber,sortDirection));
    }
    @GetMapping("/tasks/{id}")
    public Task getSingleTask(@PathVariable long id) {
        return taskService.getSingleTask(id);
    }
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }
    @PutMapping("/tasks")
    public Task editTask(@RequestBody Task task) {
        return taskService.editTask(task);
    }
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
    }
}
