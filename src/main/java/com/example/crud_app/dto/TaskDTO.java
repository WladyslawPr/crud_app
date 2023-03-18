package com.example.crud_app.dto;

import com.example.crud_app.enums.Status;
import lombok.Builder;

@Builder
public class TaskDTO {
    private Long id;
    private String description;
    private Status status;

    public TaskDTO (Long id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId () {
        return id;
    }

    public String getDescription () {
        return description;
    }

    public Status getStatus () {
        return status;
    }
}
