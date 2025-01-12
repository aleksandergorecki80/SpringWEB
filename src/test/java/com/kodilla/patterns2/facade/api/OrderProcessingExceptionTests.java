package com.kodilla.patterns2.facade.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderProcessingExceptionTests {
    @Test
    void shouldGiveMessage() {
        // Given
        String message = "Test error message";

        // When
        OrderProcessingException exception = new OrderProcessingException(message);

        // Then
        assertEquals(message, exception.getMessage());
    }
}
