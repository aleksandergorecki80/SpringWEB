package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalHttpErrorHandlerTests {

    @Test
    public void shouldReturnBadRequestWhenTaskNotFoundExceptionIsThrown() {
        // Given
        GlobalHttpErrorHandler errorHandler = new GlobalHttpErrorHandler();
        TaskNotFoundException exception = new TaskNotFoundException();

        // When
        ResponseEntity<Object> response = errorHandler.handleTaskNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Task with given id does not exist", response.getBody());
    }
}
