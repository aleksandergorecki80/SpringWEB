package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrelloControllerTests {
    @InjectMocks
    TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    void shouldGetTrelloBoards() {
        // Given
        // When
        ResponseEntity<List<TrelloBoardDto>> response = trelloController.getTrelloBoards();

        // Then
        verify(trelloFacade, times(1)).fetchTrelloBoards();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCreateTrelloCard() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("Task 1", "Description of the task", "top", "12345");

        // When
        ResponseEntity<CreatedTrelloCardDto> response = trelloController.createTrelloCard(cardDto);

        // Then
        verify(trelloFacade, times(1)).createCard(cardDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
