package com.example.cinemaroomrestservice;

import com.example.cinemaroomrestservice.exceptions.SeatNotFoundException;
import com.example.cinemaroomrestservice.exceptions.SeatPurchasedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows = 9;

    @JsonProperty("total_columns")
    private final int totalColumns = 9;

    @JsonProperty("available_seats")
    private final ArrayList<Seat> availableSeats = new ArrayList<Seat>();

    @JsonIgnore
    private final ArrayList<Seat> purchasedSeats = new ArrayList<Seat>();


    public Cinema() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                availableSeats.add(new Seat(row, column));
            }
        }
    }

    public Seat findAvailableSeat(Seat seat) {
        if (seat.getRow() > totalRows || seat.getColumn() > totalColumns ||
                seat.getRow() <= 0 || seat.getColumn() <= 0) {
            throw new SeatNotFoundException();
        }

        for (Seat s : availableSeats) {
            if (s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()) {
                availableSeats.remove(s);
                purchasedSeats.add(s);
                return s;
            }
        }

        throw new SeatPurchasedException();
    }

    public void returnFromPurchase(Seat seat) {
        for (Seat s : availableSeats) {
            if (s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn()) {
                purchasedSeats.remove(s);
                return;
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
