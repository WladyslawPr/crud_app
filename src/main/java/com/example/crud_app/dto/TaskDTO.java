package com.example.crud_app.dto;

import com.example.crud_app.enums.Status;
import lombok.Builder;

@Builder
public class TaskDTO {
    private long id;
    private String description;
    private Status status;

    public TaskDTO (long id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public long getId () {
        return id;
    }

    public String getDescription () {
        return description;
    }

    public Status getStatus () {
        return status;
    }
}
