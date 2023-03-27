package com.example.cinemaroomrestservice;

import java.util.UUID;

public class PurchaseSeat {
    private final String token;
    private final Seat seat;


    public PurchaseSeat(Seat seat) {
        this.seat = seat;
        this.token = String.valueOf(UUID.randomUUID());
    }

    public String getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }
}
