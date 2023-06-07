package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.CollectionJdbcDao;
import com.techelevator.model.*;
import com.techelevator.services.MetronCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class APIRequestController {

    @Autowired
    MetronCloud comicAPIRequest;

    @Autowired
    CollectionDao collectionDao; // Add the autowired CollectionDao

    @RequestMapping(path="/comic-character", method= RequestMethod.GET)
    public List<CharacterComicData> comics(@RequestParam String comicSearch) throws JsonMappingException, JsonProcessingException {
        return comicAPIRequest.getComicResults(comicSearch);
    }

    // TODO change path to match Vue
    @RequestMapping(path="/comic-issue", method= RequestMethod.GET)
    public ComicSpecificIssue comic(@RequestParam int issueSearch) throws JsonMappingException, JsonProcessingException {
        return comicAPIRequest.getComicById(issueSearch);
    }

    @RequestMapping(path="/CHANGE-ME", method= RequestMethod.GET)
    public List<ResultByDate> comics(@RequestParam Integer month, Integer date) throws JsonMappingException, JsonProcessingException {
        return comicAPIRequest.getComicByDate(month, date);
    }
}