package com.crud.tasks.trello.facade;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import lombok.RequiredArgsConstructor;

import com.crud.tasks.service.TrelloService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class TrelloFacade {
    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;
    private final TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        System.out.println(trelloCardDto + " trelloCardDto");

        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        System.out.println(trelloCard + " trelloCard");

        trelloValidator.validateCard(trelloCard);
        TrelloCardDto trelloCardDto1 = trelloMapper.mapToCardDto(trelloCard);
        System.out.println(trelloCardDto1 + " trelloCardDto1");
        return trelloService.createTrelloCard(trelloCardDto1);
    }

}
