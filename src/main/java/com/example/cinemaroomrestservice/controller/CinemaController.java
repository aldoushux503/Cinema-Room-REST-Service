package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.*;
import com.example.cinemaroomrestservice.exceptions.WrongPasswordException;
import com.example.cinemaroomrestservice.exceptions.WrongTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class CinemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaRoomRestServiceApplication.class);
    ObjectMapper mapper = new ObjectMapper();
    private final CinemaRoom cinemaRoom = new CinemaRoom();
    private static final ConcurrentMap<String, Ticket> availableTokensMap = new ConcurrentHashMap<>();

    @GetMapping("/seats")
    public CinemaRoom getCinemaInformation() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public BookedTicket purchaseSeat(@RequestBody String requestJson) {
        Ticket purchaseTicket = null;
        try {
            purchaseTicket = mapper.readValue(requestJson, Ticket.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(String.valueOf(e));
        }

        Ticket ticket = cinemaRoom.findAvailableSeat(purchaseTicket);
        BookedTicket purchase = new BookedTicket(ticket);
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
            cinemaRoom.returnFromPurchase(findedTicket);
            return Map.of("returned_ticket", findedTicket);
        }
        throw new WrongTokenException();
    }


    @PostMapping("/stats")
    @ResponseBody
    public Map<String, Integer> showStats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            throw new WrongPasswordException();
        }
        return cinemaRoom.calculateStatistic();
    }
}
