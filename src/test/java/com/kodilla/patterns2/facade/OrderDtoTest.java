package com.kodilla.patterns2.facade;

import com.kodilla.patterns2.facade.api.ItemDto;
import com.kodilla.patterns2.facade.api.OrderDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDtoTest {
    @Test
    void shouldGetItems() {
        // Given
        OrderDto orderDto = new OrderDto();
        ItemDto item1 = new ItemDto(1L, 2);
        ItemDto item2 = new ItemDto(2L, 3);

        orderDto.addItem(item1);
        orderDto.addItem(item2);

        // When
        List<ItemDto> result = orderDto.getItems();

        // Then
        assertEquals(2, result.size());
    }
}
