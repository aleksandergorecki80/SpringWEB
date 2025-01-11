package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTests {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void shouldGetAllTasks() {

        // Given
        List<Task> tasks = List.of(
                new Task(1L, "Test Task 1", "Content 1"),
                new Task(2L, "Test Task 2", "Content 2")
        );

        when(taskRepository.findAll()).thenReturn(tasks);

        // When
        List<Task> result = dbService.getAllTasks();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void shouldGetTaskById() throws TaskNotFoundException {

        // Given
        Task task = new Task(1L, "Test Task 1", "Content 1");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Task result = dbService.getTaskById(1L);

        // Then
        assertEquals("Test Task 1", result.getTitle());
    }

    @Test
    void shouldSaveTask() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Test Task 1", "Content 1");

        when(taskRepository.save(task)).thenReturn(task);

        // When
        Task result = dbService.saveTask(task);

        // Then
        assertEquals(task, result);
    }

    @Test
    void shouldDeleteTaskById() throws TaskNotFoundException {

        // Given
        Task task = new Task(1L, "Test Task", "Content");
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));

        // When
        dbService.deleteTaskById(1L);

        // Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
