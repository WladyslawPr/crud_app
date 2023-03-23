package com.example.crud_app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.crud_app.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.crud_app.model.Task;
import com.example.crud_app.repository.TaskRepository;

class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getTasks method when sortDirection is ASC")
    void testGetTasksAscending () {
        // given
        int page = 0;
        Sort.Direction sortDirection = Sort.Direction.ASC;
        List<Task> expectedTasks = Arrays.asList(
                new Task("Task 1", Status.PAUSED),
                new Task("Task 2", Status.IN_PROGRESS),
                new Task("Task 3", Status.DONE));
        int PAGE_SIZE = 5;
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(sortDirection, "id"));
        when(taskRepository.findAllByTasks(pageRequest)).thenReturn(expectedTasks);

        // when
        List<Task> actualTasks = taskService.getTasks(page, sortDirection);

        // then
        assertThat(actualTasks).isEqualTo(expectedTasks);
    }

    @Test
    @DisplayName("Test getTasks method when sortDirection is DESC")
    void testGetTasksDescending() {
        // given
        int page = 0;
        Sort.Direction sortDirection = Sort.Direction.DESC;
        List<Task> expectedTasks = Arrays.asList(
                new Task("Task 1", Status.DONE),
                new Task("Task 2", Status.PAUSED),
                new Task("Task 3", Status.DONE),
                new Task("Task 4", Status.DONE),
                new Task("Task 5", Status.IN_PROGRESS),
                new Task("Task 6", Status.PAUSED),
                new Task("Task 7", Status.DONE),
                new Task("Task 8", Status.PAUSED),
                new Task("Task 9", Status.DONE),
                new Task("Task 10", Status.DONE),
                new Task("Task 11", Status.IN_PROGRESS),
                new Task("Task 12", Status.PAUSED));
        int PAGE_SIZE = 5;
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(sortDirection, "id"));
        when(taskRepository.findAllByTasks(pageRequest)).thenReturn(expectedTasks);

        // when
        List<Task> actualTasks = taskService.getTasks(page, sortDirection);

        // then
        assertThat(actualTasks).isEqualTo(expectedTasks);
    }

  /*  @Test // This is tested need fixing
    @DisplayName("Test getSingleTask method with caching")
    void testGetSingleTaskWithCaching() {
        // given
        long taskId = 1L;
        Task expectedTask = new Task("Task 1", Status.IN_PROGRESS);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(expectedTask));

        // when
        Task actualTask1 = taskService.getSingleTask(taskId);
        Task actualTask2 = taskService.getSingleTask(taskId);

        // then
        assertThat(actualTask1).isEqualTo(expectedTask);
        assertThat(actualTask2).isEqualTo(expectedTask);

        // Verify that the repository method is only called once
        verify(taskRepository, times(1)).findById(taskId);
    }

   */




    @Test
    void testGetSingleTaskReturnsTaskFromRepository() {
        // given
        long taskId = 1L;
        Task expectedTask = new Task("Task 1", Status.IN_PROGRESS);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(expectedTask));

        // when
        Task actualTask = taskService.getSingleTask(taskId);

        // then
        assertThat(actualTask).isEqualTo(expectedTask);
    }

}