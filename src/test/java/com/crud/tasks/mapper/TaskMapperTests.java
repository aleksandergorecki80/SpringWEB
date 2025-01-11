package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTests {
    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void testMapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task Dto 1", "Description Dto 1");

        // When
        Task result = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Task Dto 1", result.getTitle());
        assertEquals("Description Dto 1", result.getContent());
    };

    @Test
    void mapToTaskDto() {
        // Given
        Task task = new Task(101L, "Task 101", "Description 101");

        // When
        TaskDto result = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(101L, result.getId());
        assertEquals("Task 101", result.getTitle());
        assertEquals("Description 101", result.getContent());
    };

    @Test
    void mapToTaskDtoList() {
        // Given
        Task task1 = new Task(101L, "Task 101", "Description 101");
        Task task2 = new Task(202L, "Task 202", "Description 202");

        List<Task> tasks = List.of(task1, task2);

        // When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(tasks);

        // Then
        assertEquals(2, result.size());
    };
}
