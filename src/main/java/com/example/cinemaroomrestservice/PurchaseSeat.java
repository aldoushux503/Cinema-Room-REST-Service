package com.example.cinemaroomrestservice;

import java.util.UUID;

public class PurchaseSeat {
    private final Token token;
    private final Seat seat;


    public PurchaseSeat(Seat seat) {
        this.seat = seat;

        String randomToken = String.valueOf(UUID.randomUUID());
        this.token = new Token(randomToken);
    }

    public String getToken() {
        return token.getValue();
    }

    public Seat getSeat() {
        return seat;
    }
}