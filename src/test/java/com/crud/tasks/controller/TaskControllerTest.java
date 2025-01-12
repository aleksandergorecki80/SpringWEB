package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
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

    @Test
    void shouldFetchTaskById() throws Exception {
        // Given
        Long id = 1234L;
        Task task = new Task(101L, "Task 101", "Description 101");
        TaskDto taskDto = new TaskDto(101L, "Task 101", "Description 101");

        when(service.getTaskById(id)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/{taskId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(101)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task 101")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Description 101")));
    };

    @Test
    void shouldDeleteTaskById() throws Exception {
        // Given
        Long id = 1234L;

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/{taskId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    };

    @Test
    void shouldUpdateTask() throws Exception {
        // Given
        Task task = new Task(101L, "Task 101", "Description 101");
        TaskDto taskDto = new TaskDto(101L, "Task 101", "Description 101");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Task 101"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Description 101"));
    };

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        Task task = new Task(303L, "Task 303", "Description 303");
        TaskDto taskDto = new TaskDto(303L, "Task 303", "Description 303");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

















