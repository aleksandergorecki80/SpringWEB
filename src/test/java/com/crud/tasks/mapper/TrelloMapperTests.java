package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
    void testMapToList() {
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

    @Test
    void testMapToBoards() {
        // Given
        TrelloListDto list1 = new TrelloListDto("1", "To Do", false);
        TrelloListDto list2 = new TrelloListDto("2", "Done", true);
        TrelloListDto list3 = new TrelloListDto("3", "To Do 2", false);
        TrelloListDto list4 = new TrelloListDto("4", "Done 2", true);

        List<TrelloListDto> trelloListDtoList1= new ArrayList<>();
        trelloListDtoList1.add(list1);
        trelloListDtoList1.add(list2);

        List<TrelloListDto> trelloListDtoList2= new ArrayList<>();
        trelloListDtoList1.add(list3);
        trelloListDtoList1.add(list4);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "Board 1", trelloListDtoList1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Board 2", trelloListDtoList2);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);


        // When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardsDto);

        // Then
        assertEquals(2, result.size());

        assertEquals("1", trelloBoardsDto.getFirst().getId());
        assertEquals("Board 2", trelloBoardsDto.getLast().getName());

    }
}
