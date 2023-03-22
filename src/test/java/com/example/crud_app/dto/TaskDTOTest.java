package com.example.crud_app.dto;

import com.example.crud_app.enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskDTOTest {

    @Test
    public void testConstructorAndGetters () {
        long id = 1L;
        String description = "Task description";
        Status status = Status.DONE;
        TaskDTO taskDTO = new TaskDTO(id, description, status);
        assertNotNull(taskDTO);
        assertEquals(id, taskDTO.getId());
        assertEquals(description, taskDTO.getDescription());
        assertEquals(status, taskDTO.getStatus());
    }

    @Test
    public void testBuilder () {
        long id = 1L;
        String description = "Task description";
        Status status = Status.DONE;
        TaskDTO taskDTO = TaskDTO.builder()
                .id(id)
                .description(description)
                .status(status)
                .build();
        assertNotNull(taskDTO);
        assertEquals(id, taskDTO.getId());
        assertEquals(description, taskDTO.getDescription());
        assertEquals(status, taskDTO.getStatus());
    }
}