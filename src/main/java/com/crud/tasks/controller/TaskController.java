package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks/")
public class TaskController {

  private final DbService service;
  private final TaskMapper taskMapper;

  public TaskController(final DbService service, final TaskMapper taskMapper) {
    this.service = service;
    this.taskMapper = taskMapper;
  }

  @GetMapping
  public List<TaskDto> getTasks() {
    List<Task> tasks = service.getAllTasks();
    return taskMapper.mapToTaskDtoList(tasks);
  }

  @GetMapping(value = "{taskId}")
  public TaskDto getTask(@PathVariable Long taskId) {
    return service.getTaskById(taskId)
        .map(taskMapper::mapToTaskDto)
        .orElseThrow(() -> new RuntimeException("No task found"));
  }

  @DeleteMapping(value = "{taskId}")
  public void deleteTask(@PathVariable Long taskId) {

  }

  @PutMapping(value = "{taskId}")
  public TaskDto updateTask(@PathVariable Long taskId) {
    return new TaskDto(1L, "Edited test title", "test content");
  }

  @PostMapping
  public void createTask(TaskDto taskDto) {
  }
}
