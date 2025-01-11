package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTests {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private  SimpleEmailService emailService;

    @Mock
    private  AdminConfig adminConfig;

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        // Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        System.out.println(trelloBoardDtos);
        trelloBoardDtos.forEach(trelloBoardDto -> {

            assertEquals("1", trelloBoardDto.getId());
            assertEquals("test", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("test_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    void shouldCreateTrelloCard() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("Task 1", "Description of the task", "top", "12345");


        Trello trello = new Trello(5, 10);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(attachmentsByType);

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "12345",
                "Test Card",
                "http://short.url/12345",
                badges);
        when(trelloClient.createNewCard(cardDto)).thenReturn(createdTrelloCardDto);


        // When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(cardDto);

        // Then
        assertEquals(createdTrelloCardDto, result);
    }

}
