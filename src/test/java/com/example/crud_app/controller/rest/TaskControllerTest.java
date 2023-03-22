package com.example.crud_app.controller.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.example.crud_app.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.crud_app.dto.TaskDTO;
import com.example.crud_app.mapper.TaskDTOMapper;
import com.example.crud_app.model.Task;
import com.example.crud_app.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    private TaskController taskController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        taskController = new TaskController(taskService);
    }

    @Test
    void testGetTasks() throws Exception {

        Task task1 = new Task("task1", Status.DONE);
        Task task2 = new Task("task2", Status.PAUSED);
        List<Task> tasks = Arrays.asList(task1, task2);
        List<TaskDTO> taskDTOs = TaskDTOMapper.mapToTaskDTOs(tasks);
        when(taskService.getTasks(anyInt(), any())).thenReturn(tasks);

        List<TaskDTO> result = taskController.getTasks(0, Sort.Direction.ASC, getTestUser());

        Comparator<TaskDTO> taskDTOComparator = Comparator.comparing(TaskDTO::getId);
        taskDTOs.sort(taskDTOComparator);
        result.sort(taskDTOComparator);

        verify(taskService).getTasks(eq(0), eq(Sort.Direction.ASC));
        assertThat(result, hasSize(2));
        //  assertThat(result, containsInAnyOrder(taskDTOs.toArray()));
        //  assertThat(result, contains(taskDTOs.toArray()));

    }

    private UsernamePasswordAuthenticationToken getTestUser() {

        UserDetails user = User.builder()
                .username("testUser")
                .password("password")
                .authorities(new SimpleGrantedAuthority("USER"))
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Test
    void testGetSingleTask() throws Exception {

        Task task = new Task("task1", Status.DONE);
        when(taskService.getSingleTask(1L)).thenReturn(task);

        Task result = taskController.getSingleTask(1L);

        verify(taskService).getSingleTask(1L);
        assertEquals(task, result);
    }

    @Test
    void testAddTask() throws Exception {
        Task task = new Task("task1", Status.IN_PROGRESS);
        when(taskService.addTask(task)).thenReturn(task);

        String requestBody = objectMapper.writeValueAsString(task);

        Task result = taskController.addTask(task);

        verify(taskService).addTask(task);
        assertEquals(task, result);
    }

    @Test
    void testEditTask() throws Exception {
        Task task = new Task("task1", Status.DONE);
        task.setId(1L);

        Task updatedTask = new Task("task1-updated", Status.PAUSED);
        updatedTask.setId(1L);

        // mock the taskService to return the updated task
        when(taskService.editTask(task)).thenReturn(updatedTask);

        // call the editTask method with the original task
        Task result = taskController.editTask(task);

        verify(taskService).editTask(task);
        assertEquals(updatedTask, result);
    }

    @Test
    void testDeleteTask() throws Exception {

        Task task = new Task("task1", Status.DONE);
        task.setId(1L);

        taskController.deleteTask(task.getId());

        verify(taskService).deleteTask(task.getId());
    }
}