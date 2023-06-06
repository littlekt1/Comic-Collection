package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.techelevator.model.Comic;

import java.util.ArrayList;
import java.util.List;

    //TODO CHECK application.properties.


@Component
public class MetronCloud {

    @Value("${metron.cloud.api.username}")
    String username;

    @Value("${metron.cloud.api.password}")
    String password;

    @Value("${metron.cloud.api.search.url}")
    String searchURL;

    public List<Comic> getComicResults(String comicSearchEntry) throws JsonMappingException, JsonProcessingException {


        //TODO NEEDS DISCUSSED HOW WE WANT TO PULL THINGS
        String url = this.searchURL + "character/?name=" + comicSearchEntry;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.username, this.password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        List<Comic> listOfCharacterId = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String id = jsonNode.path("results").asText();
            Comic characterId = new Comic(id);
            listOfCharacterId.add(characterId);
        }
        return listOfCharacterId;
        }
    }



