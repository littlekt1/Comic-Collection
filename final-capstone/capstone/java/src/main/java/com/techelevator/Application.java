package com.techelevator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.model.Comic;
import com.techelevator.services.MetronCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class Application {


    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(Application.class, args);
           }

    }

