package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CinemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaRoomRestServiceApplication.class);
    private final Cinema cinema = new Cinema();
    private static final Map<String, Seat> availableTokensMap = new HashMap<>();

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
        PurchaseSeat purchase = new PurchaseSeat(seat);
        availableTokensMap.put(purchase.getToken(), seat);

        return purchase;
    }


    @PostMapping("/return")
    public Seat returnTicket(@RequestBody String requestJson) {
        ObjectMapper mapper = new ObjectMapper();

        Token token = null;
        try {
            token = mapper.readValue(requestJson, Token.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.valueOf(e));
        }
        String findingTokenValue = token.getValue();

        if (availableTokensMap.containsKey(findingTokenValue)) {
            return availableTokensMap.get(findingTokenValue);
        }

        return null;
    }
}
