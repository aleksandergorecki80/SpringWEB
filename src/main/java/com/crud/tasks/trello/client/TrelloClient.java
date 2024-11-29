package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TrelloClient {
  private final RestTemplate restTemplate;

  @Value("${trello.api.endpoint.prod}")
  private String trelloApiEndpoint;
  @Value("${trello.app.key}")
  private String trelloAppKey;
  @Value("${trello.app.token}")
  private String trelloToken;
  @Value("${trello.app.userIdMe}")
  private String trelloUserIdMe;

  public List<TrelloBoardDto> getTrelloBoards() {
    URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUserIdMe + "/boards")
        .queryParam("key", trelloAppKey)
        .queryParam("token", trelloToken)
        .queryParam("fields", "name,id")
        .build()
        .encode()
        .toUri();

    TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

    return Optional.ofNullable(boardsResponse)
        .map(Arrays::asList)
        .orElse(Collections.emptyList());

//    if(boardsResponse !=null) {
//      return Arrays.asList(boardsResponse);
//    }
//
//    return new ArrayList<>();

//    return Collections.emptyList();

//    TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
//        trelloApiEndpoint + "/members/kodillaautor/boards" + "?key=" + trelloAppKey + "&token=" + trelloToken,
//        TrelloBoardDto[].class
//    );
  };
}
