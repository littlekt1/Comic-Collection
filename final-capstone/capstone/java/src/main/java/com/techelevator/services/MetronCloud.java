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

    public String getToken() throws JsonMappingException, JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(this.username, this.password);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


//        TODO *** I UNDERSTAND HOW THIS WORKS WITH REDDIT, NOT METRONCLOUD
//        httpHeaders.set("User-Agent", "reddit-parser");
//        String body = "grant_type=client_credentials";
//        HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);
//        ResponseEntity<String> response = restTemplate.exchange(this.oauthURL, HttpMethod.POST, request, String.class);

//        ObjectMapper objectMapper = new ObjectMapper();
//       JsonNode jsonNode = objectMapper.readTree(response.getBody());
//        String token = jsonNode.path("access_token").asText();

//        return token;
        return null; //just to kill redline
    }

    public List<Comic> getComicResults(String token, String comicSearchEntry) throws JsonMappingException, JsonProcessingException {


        //TODO NEEDS DISCUSSED HOW WE WANT TO PULL THINGS
        String url = this.searchURL + comicSearchEntry + "/hot";
        token = "Bearer " + token;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //TODO NEED TO UNDERSTAND THE SET HEADERS PORTION
        headers.set("User-Agent", "reddit-parser");
        headers.set("Authorization", token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        List<Comic> listOfComics = new ArrayList<Comic>();

        for (int i=0; i < 10; i++) {

            String title = jsonNode.path("data").path("children").path(i).path("data").path("title").asText();
            String ups = jsonNode.path("data").path("children").path(i).path("data").path("ups").asText();
            String image = jsonNode.path("data").path("children").path(i).path("data").path("url").asText();

            // TODO getting things aren't 100% straight forward. I think we may need to make a few calls to get back what we
            // specifically asking for
            Comic comics = new Comic(); //TODO need to define what we want back for a comic
            listOfComics.add(comics);

        }

        return listOfComics;
    }


}
