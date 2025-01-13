package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTests {
    @InjectMocks
    TaskController taskController;

    @Mock
    private DbService service;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void shouldGetTasks() {
        // Given
        List<TaskDto> tasksDto = List.of(new TaskDto(1L, "Test", "Content"));
        List<Task> tasks = List.of(new Task(1L, "Test", "Content"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        // When
        ResponseEntity<List<TaskDto>> response = taskController.getTasks();

        // Then
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void shouldGetSingleTask() throws TaskNotFoundException {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Content");
        Task task = new Task(1L, "Test", "Content");

        when(service.getTaskById(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.getTask(1L);

        // Then
        assertEquals("Test", Objects.requireNonNull(response.getBody()).getTitle());
    }

    @Test
    void shouldDeleteTask() throws TaskNotFoundException {
        // Given
        Long taskId = 1L;

        // When
        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        // Then
        verify(service, times(1)).deleteTaskById(taskId);
    }

    @Test
    void shouldUpdateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Content");
        Task task = new Task(1L, "Test", "Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.updateTask(taskDto);

        // Then
        assertEquals("Test", Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCreateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Content");
        Task task = new Task(1L, "Test", "Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        // When
        ResponseEntity<Void> response = taskController.createTask(taskDto);

        // Then
        verify(service, times(1)).saveTask(task);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
