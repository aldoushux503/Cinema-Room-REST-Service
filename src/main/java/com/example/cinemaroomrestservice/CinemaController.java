package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    @GetMapping("/seats")
    public String getCinemaInformation() throws JsonProcessingException {
        Cinema cinema = new Cinema();
        ObjectMapper mapper = new ObjectMapper();

        String res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cinema);

        return res;
    }

}
