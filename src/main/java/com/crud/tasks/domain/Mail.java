package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class Mail {
  private final String mailTo;
  private final String subject;
  private final String message;
  private final String toCc;
  private final List<String> tasks;

  public Optional<String> getToCc() {

    return Optional.ofNullable(toCc);
  }

  public Optional<List<String>> getTasks() {
    return Optional.ofNullable(tasks);
  }
}