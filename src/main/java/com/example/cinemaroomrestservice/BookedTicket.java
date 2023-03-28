package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BookedTicket {

    @JsonProperty("token")
    private final Token token;
    @JsonProperty("ticket")
    private final Ticket ticket;


    public BookedTicket(Ticket ticket) {
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
