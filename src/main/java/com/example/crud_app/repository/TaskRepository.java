package com.example.crud_app.repository;

import com.example.crud_app.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t")
    List<Task> findAllByTasks(Pageable page);
}
