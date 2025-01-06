package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTests {
    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void testMapToCard() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("Task 1", "Description of the task", "top", "12345");
        TrelloCard expectedResult = new TrelloCard("Task 1", "Description of the task", "top", "12345");

        // When
        TrelloCard result = trelloMapper.mapToCard(cardDto);

        // Then
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getDescription(), result.getDescription());
        assertEquals(expectedResult.getPos(), result.getPos());
        assertEquals(expectedResult.getListId(), result.getListId());
    }

    @Test
    void mapToListTest() {
        // Given
        TrelloListDto list1 = new TrelloListDto("1", "To Do", false);
        TrelloListDto list2 = new TrelloListDto("2", "Done", true);

        List<TrelloListDto> trelloListDtoList= new ArrayList<>();
        trelloListDtoList.add(list1);
        trelloListDtoList.add(list2);

        // When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtoList);

        // Then
        assertEquals(2, result.size());

        assertEquals(result.getFirst().getId(), trelloListDtoList.getFirst().getId());
        assertEquals(result.getFirst().getName(), trelloListDtoList.getFirst().getName());
        assertEquals(result.getFirst().isClosed(), trelloListDtoList.getFirst().isClosed());

        assertEquals(result.getLast().getId(), trelloListDtoList.getLast().getId());
        assertEquals(result.getLast().getName(), trelloListDtoList.getLast().getName());
        assertEquals(result.getLast().isClosed(), trelloListDtoList.getLast().isClosed());
    }
}
