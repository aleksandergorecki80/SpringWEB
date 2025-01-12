package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchTasks() throws Exception {
        // Given
        Task task1 = new Task(101L, "Task 101", "Description 101");
        Task task2 = new Task(202L, "Task 202", "Description 202");
        List<Task> tasks = List.of(task1, task2);

        TaskDto taskDto1 = new TaskDto(101L, "Task 101", "Description 101");
        TaskDto taskDto2 = new TaskDto(202L, "Task 202", "Description 202");

        List<TaskDto> tasksDto = List.of(taskDto1, taskDto2);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(101)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Task 101")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Description 101")))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(202)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Task 202")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("Description 202")));
    }
}

















