package com.example.crud_app.service;

import com.example.crud_app.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    Task getSingleTask (Long id);
}
