package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTests {

    @InjectMocks
    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Mock
    private Logger logger;

    @Test
    void shouldValidateTrelloBoards() {
        // Given
        TrelloList list1 = new TrelloList("1", "To Do", false);
        TrelloList list2 = new TrelloList("2", "Done", true);
        TrelloList list3 = new TrelloList("3", "To Do 2", false);
        TrelloList list4 = new TrelloList("4", "Done 2", true);

        List<TrelloList> trelloListList1= new ArrayList<>();
        trelloListList1.add(list1);
        trelloListList1.add(list2);

        List<TrelloList> trelloListList2= new ArrayList<>();
        trelloListList2.add(list3);
        trelloListList2.add(list4);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Board 1", trelloListList1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test", trelloListList2);

        List<TrelloBoard> trelloBoards = List.of(trelloBoard1, trelloBoard2);

        // When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        // Then
        assertEquals(1, result.size());
    }
}
