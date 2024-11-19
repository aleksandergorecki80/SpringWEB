package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
  private final TaskRepository repository;

  public List<Task> getAllTasks() {
    return repository.findAll();
  }

  public Task getTaskById(Long taskId) throws TaskNotFoundException {
    return repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
  }

  public Task saveTask(final Task task) {
    return repository.save(task);
  }

  public void deleteTaskById(Long taskId) throws TaskNotFoundException {
    repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    repository.deleteById(taskId);
  }
}
