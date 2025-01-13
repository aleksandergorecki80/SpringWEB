package com.crud.tasks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name= "tasks")
public class Task {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String title;

  @Column(name = "description")
  private String content;
}
