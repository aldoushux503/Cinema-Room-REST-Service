package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.*;
import com.example.cinemaroomrestservice.exceptions.WrongTokenException;
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
    ObjectMapper mapper = new ObjectMapper();
    private final Cinema cinema = new Cinema();
    private static final Map<String, Ticket> availableTokensMap = new HashMap<>();

    @GetMapping("/seats")
    public Cinema getCinemaInformation() {
        return cinema;
    }

    @PostMapping("/purchase")
    public PurchaseSeat purchaseSeat(@RequestBody String requestJson) {
        Ticket purchaseTicket = null;
        try {
            purchaseTicket = mapper.readValue(requestJson, Ticket.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.valueOf(e));
        }

        Ticket ticket = cinema.findAvailableSeat(purchaseTicket);
        PurchaseSeat purchase = new PurchaseSeat(ticket);
        availableTokensMap.put(purchase.getToken(), ticket);

        return purchase;
    }

    @PostMapping("/return")
    public Map<String, Ticket> returnTicket(@RequestBody String requestJson) {
        String token = null;
        try {
            token = mapper.readValue(requestJson, Token.class).getValue();
        } catch (JsonProcessingException e) {
            LOGGER.error(String.valueOf(e));
        }

        if (availableTokensMap.containsKey(token)) {
            Ticket findedTicket = availableTokensMap.get(token);
            cinema.returnFromPurchase(findedTicket);
            return Map.of("returned_ticket", findedTicket);
        }
        throw new WrongTokenException();
    }
}
