package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public List<CharacterComicData> getComicResults(String comicSearchEntry) throws JsonMappingException, JsonProcessingException {


        //TODO NEEDS DISCUSSED HOW WE WANT TO PULL THINGS
        String url = this.searchURL + "character/?name=" + comicSearchEntry;



        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.username, this.password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ComicData comicData = objectMapper.readValue(response.getBody(), ComicData.class);
        List<Result> results = comicData.getResults();
        Integer comicSearchId = null;
        for (Result result : results) {
            if (result.getName().equalsIgnoreCase(comicSearchEntry)) {
                comicSearchId = result.getId();
            }
        }
        if (comicSearchId != null) {
            String urlComicSearch = this.searchURL + "character/" + comicSearchId + "/issue_list/";
            ResponseEntity<String> comicResponse = restTemplate.exchange(urlComicSearch, HttpMethod.GET, httpEntity, String.class);
            CharacterData characterData = objectMapper.readValue(comicResponse.getBody(), CharacterData.class);
            return characterData.getResults();


        }





        return null;
    }

    }



