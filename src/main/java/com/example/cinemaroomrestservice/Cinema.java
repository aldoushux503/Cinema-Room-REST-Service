package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows = 9;

    @JsonProperty("total_columns")
    private final int totalColumns = 9;

    @JsonProperty("available_seats")
    private final ArrayList<Seat> availableSeats = new ArrayList<Seat>();


    public Cinema() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                availableSeats.add(new Seat(row, column));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }
}
