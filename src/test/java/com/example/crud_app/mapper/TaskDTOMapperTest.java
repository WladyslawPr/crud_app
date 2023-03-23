package com.example.crud_app.mapper;

import com.example.crud_app.dto.TaskDTO;
import com.example.crud_app.enums.Status;
import com.example.crud_app.model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskDTOMapperTest {

    @Test
    void mapToTaskDTOs() {
        Task task1 = new Task("Task 1", Status.IN_PROGRESS);
        Task task2 = new Task("Task 2", Status.DONE);
        Task task3 = new Task("Task 3", Status.PAUSED);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        List<TaskDTO> taskDTOs = TaskDTOMapper.mapToTaskDTOs(tasks);

        assertEquals(taskDTOs.size(), tasks.size());
        assertEquals(taskDTOs.get(0).getDescription(), task1.getDescription());
        assertEquals(taskDTOs.get(1).getStatus(), task2.getStatus());
        assertEquals(taskDTOs.get(2).getStatus(), task3.getStatus());
    }

    @Test
    void mapToTaskDTO() {
        Task task = new Task("Task 1", Status.DONE);

        TaskDTO taskDTO = TaskDTOMapper.mapToTaskDTO(task);

        assertEquals(taskDTO.getId(), task.getId());
        assertEquals(taskDTO.getDescription(), task.getDescription());
        assertEquals(taskDTO.getStatus(), task.getStatus());
    }
}
