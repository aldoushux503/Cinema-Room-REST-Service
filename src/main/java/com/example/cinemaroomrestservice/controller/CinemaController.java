package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.exceptions.WrongPasswordException;
import com.example.cinemaroomrestservice.exceptions.WrongTokenException;
import com.example.cinemaroomrestservice.model.BookedTicket;
import com.example.cinemaroomrestservice.model.CinemaRoom;
import com.example.cinemaroomrestservice.model.Ticket;
import com.example.cinemaroomrestservice.model.Token;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class CinemaController {

    private final CinemaRoom cinemaRoom = new CinemaRoom();
    private static final ConcurrentMap<String, Ticket> availableTokensMap = new ConcurrentHashMap<>();

    @GetMapping("/seats")
    public CinemaRoom getCinemaInformation() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public BookedTicket purchaseSeat(@RequestBody Ticket purchaseTicket) {
        Ticket ticket = cinemaRoom.findAvailableSeat(purchaseTicket);
        BookedTicket purchase = new BookedTicket(ticket);
        availableTokensMap.put(purchase.getToken(), ticket);

        return purchase;
    }

    @PostMapping("/return")
    public Map<String, Ticket> returnTicket(@RequestBody Token token) {
        String tokenValue = token.getValue();

        if (availableTokensMap.containsKey(tokenValue)) {
            Ticket findedTicket = availableTokensMap.get(tokenValue);
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
