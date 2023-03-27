package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaRoomRestServiceApplication.class);
    private final Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinemaInformation(@RequestBody String requestJson) {
        return cinema;
    }


    @PostMapping("/purchase")
    public Seat purchaseSeat(@RequestBody String requestJson) {
        ObjectMapper mapper = new ObjectMapper();

        Seat purchaseSeat = null;
        try {
            purchaseSeat = mapper.readValue(requestJson, Seat.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.valueOf(e));
        }

        return cinema.findAvailableSeat(purchaseSeat);
    }

}
