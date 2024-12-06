package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Optional;

//@Getter
//@AllArgsConstructor
@Data
@Builder
public class Mail {
  private final String mailTo;
  private final String subject;
  private final String message;
  private final Optional<String> toCc;
}