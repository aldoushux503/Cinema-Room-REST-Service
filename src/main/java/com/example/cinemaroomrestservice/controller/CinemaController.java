package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.Cinema;
import com.example.cinemaroomrestservice.CinemaRoomRestServiceApplication;
import com.example.cinemaroomrestservice.PurchaseSeat;
import com.example.cinemaroomrestservice.Seat;
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
    public PurchaseSeat purchaseSeat(@RequestBody String requestJson) {
        ObjectMapper mapper = new ObjectMapper();

        Seat purchaseSeat = null;
        try {
            purchaseSeat = mapper.readValue(requestJson, Seat.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.valueOf(e));
        }

        Seat seat = cinema.findAvailableSeat(purchaseSeat);

        return new PurchaseSeat(seat);
    }

}
