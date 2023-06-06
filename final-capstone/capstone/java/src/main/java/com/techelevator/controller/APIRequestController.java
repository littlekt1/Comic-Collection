package com.techelevator.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.techelevator.dao.CollectionJdbcDao;
import com.techelevator.model.CharacterComicData;
import com.techelevator.model.Comic;
import com.techelevator.services.MetronCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//TODO SHOULD GET TOKEN THEN SUPPLY TOKEN AND REQUEST TO API

@CrossOrigin
@RestController
public class APIRequestController {

    @Autowired
    MetronCloud comicAPIRequest;

    @RequestMapping(path="/woof", method=RequestMethod.GET)
    public void test() {
        System.out.println("u thar?");
    }


    @RequestMapping(path="/comic-character", method= RequestMethod.GET)
    public List<CharacterComicData> comics(@RequestParam String comicSearch) throws JsonMappingException, JsonProcessingException {

        return comicAPIRequest.getComicResults(comicSearch);

    }
}


