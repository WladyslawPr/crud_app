package com.example.crud_app.mapper;

import com.example.crud_app.dto.TaskDTO;
import com.example.crud_app.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDTOMapper {

    private TaskDTOMapper() {}

    public static List<TaskDTO> mapToTaskDTOs (List<Task> tasks) {
        return tasks.stream()
                .map(task -> mapToTaskDTO(task))
                .collect(Collectors.toList());
    }
    private static TaskDTO mapToTaskDTO (Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}
