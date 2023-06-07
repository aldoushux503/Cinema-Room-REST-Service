package com.example.cinemaroomrestservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Ticket(int row, int column, int price) {
    public Ticket(@JsonProperty("row") int row, @JsonProperty("column") int column, @JsonProperty("price") int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }
}
