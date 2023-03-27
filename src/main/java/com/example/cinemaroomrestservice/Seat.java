package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private final int row;
    private final int column;

    private final int price;

    public Seat(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;

        //If the row number <= to 4, the price will be 10, other rows cost 8 per seat.
        this.price = row <= 4 ? 10 : 8;
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
