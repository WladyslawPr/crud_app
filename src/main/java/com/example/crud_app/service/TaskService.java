package com.example.crud_app.service;

import com.example.crud_app.model.Task;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TaskService {
    List<Task> getTasks(Integer page, Sort.Direction sort);
    Task getSingleTask (Long id);
}
