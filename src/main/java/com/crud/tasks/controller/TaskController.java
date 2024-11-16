package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
  public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) {
    try {
      return new ResponseEntity<>(taskMapper.mapToTaskDto(service.getTaskById(taskId)), HttpStatus.OK);
    } catch (TaskNotFoundException e) {
      return new ResponseEntity<>(
          new TaskDto(0L, "There is no task with id equals " + taskId, ""), HttpStatus.NOT_FOUND
      );
    }
  }

  @DeleteMapping(value = "{taskId}")
  public void deleteTask(@PathVariable Long taskId) {

  }

  @PutMapping(value = "{taskId}")
  public TaskDto updateTask(@PathVariable Long taskId) {
    return new TaskDto(1L, "Edited test title", "test content");
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createTask(@RequestBody TaskDto taskDto) {
    Task task = taskMapper.mapToTask(taskDto);
    service.saveTask(task);
  }
}
