package com.techelevator.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.techelevator.dao.CollectionJdbcDao;
import com.techelevator.model.*;
import com.techelevator.services.MetronCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class APIRequestController {

    @Autowired
    MetronCloud comicAPIRequest;

    @RequestMapping(path="/comic-character", method= RequestMethod.GET)
    public List<CharacterComicData> comics(@RequestParam String comicSearch) throws JsonMappingException, JsonProcessingException {
        return comicAPIRequest.getComicResults(comicSearch);
    }

    //TODO change path to match vue
    @RequestMapping(path="/comic-issue", method= RequestMethod.GET)
    public ComicSpecificIssue comic(@RequestParam int issueSearch) throws JsonMappingException, JsonProcessingException {
        return comicAPIRequest.getComicById(issueSearch);
    }

    @RequestMapping(path="/get-random", method= RequestMethod.GET)
    public List<ComicSpecificIssue> random() throws JsonProcessingException {
        List<ComicSpecificIssue> randomComics = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int comicId = (int)(Math.floor(Math.random() * 60000));
            randomComics.add(comicAPIRequest.getComicById(comicId));
        }
        return randomComics;
    }
    @RequestMapping(path="/", method= RequestMethod.GET)
    public List<ResultByDate> comics(@RequestParam Integer month, Integer date) throws JsonMappingException, JsonProcessingException {
        return comicAPIRequest.getComicByDate(month, date);
    }


}


