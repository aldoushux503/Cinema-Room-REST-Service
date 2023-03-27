package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PurchaseSeat {
    private final Token token;
    @JsonProperty("ticket")
    private final Ticket ticket;


    public PurchaseSeat(Ticket ticket) {
        this.ticket = ticket;
        this.token = Token.generateToken();
    }

    public String getToken() {
        return token.getValue();
    }

    public Ticket getTicket() {
        return ticket;
    }
}
