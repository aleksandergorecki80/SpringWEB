package com.kodilla.patterns2.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Test
    void shouldGetPrice() {
        // Given
        Long id = 101L;

        // When
        BigDecimal price = productService.getPrice(id);

        // Then
        assertNotNull(price);

    }
}
