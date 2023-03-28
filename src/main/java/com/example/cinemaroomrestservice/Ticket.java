package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    private final int row;
    private final int column;

    private final int price;

    public Ticket(@JsonProperty("row") int row, @JsonProperty("column") int column, @JsonProperty("price") int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
