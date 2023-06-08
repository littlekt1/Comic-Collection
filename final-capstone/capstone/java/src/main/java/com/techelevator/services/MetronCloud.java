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


    //character search
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

    public List<ResultByDate> getComicByDate(Integer month, Integer year) throws JsonMappingException, JsonProcessingException {


        //TODO NEEDS DISCUSSED HOW WE WANT TO PULL THINGS
        String url = this.searchURL + "/issue/?cover_month=" + month + "&cover_year=" + year;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.username, this.password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        IssueByDate comicByDate = objectMapper.readValue(response.getBody(), IssueByDate.class);
        List<ResultByDate> resultsByDate = comicByDate.getResultsByDate();
        String searchDate = year + "-" + month;
        for (ResultByDate mth : resultsByDate) {
            if (mth.getCoverDate().substring(0, 8).equals(searchDate)) {
                resultsByDate.add(mth);
            }

        }
        return resultsByDate;
    }

    public ComicSpecificIssue getComicById(int comicId) throws JsonMappingException, JsonProcessingException {


        //TODO NEEDS DISCUSSED HOW WE WANT TO PULL THINGS
        String url = this.searchURL + "issue/" + comicId + "/";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.username, this.password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ComicSpecificIssue indIssue = objectMapper.readValue(response.getBody(), ComicSpecificIssue.class);
        return indIssue;

    }

    public int[] getCounts() throws JsonProcessingException {

        String supermanURL = this.searchURL + "character/13/issue_list";

        System.out.println(supermanURL);


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.username, this.password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(supermanURL, HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response.getBody());
        int supermanCount = node.path("results").size();


        String spidermanURL = this.searchURL + "character/145/issue_list";

        System.out.println(spidermanURL);

        ResponseEntity<String> response2 = restTemplate.exchange(spidermanURL, HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper2 = new ObjectMapper();
        JsonNode node2 = objectMapper2.readTree(response.getBody());
        int spidermanCount = node2.path("results").size();

        System.out.println(supermanCount);
        System.out.println(spidermanCount);

        return null;
    }

}



