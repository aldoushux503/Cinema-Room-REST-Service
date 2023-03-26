package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinemaInformation() {
        return cinema;
    }

}
