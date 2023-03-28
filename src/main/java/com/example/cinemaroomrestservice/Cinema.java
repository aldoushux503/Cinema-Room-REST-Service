package com.example.cinemaroomrestservice;

import com.example.cinemaroomrestservice.exceptions.SeatNotFoundException;
import com.example.cinemaroomrestservice.exceptions.SeatPurchasedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows = 9;

    @JsonProperty("total_columns")
    private final int totalColumns = 9;

    @JsonProperty("available_seats")
    private final ArrayList<Ticket> availableTickets = new ArrayList<>();

    @JsonIgnore
    private final ArrayList<Ticket> purchasedTickets = new ArrayList<>();


    public Cinema() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                availableTickets.add(new Ticket(row, column));
            }
        }
    }

    public Ticket findAvailableSeat(Ticket ticket) {
        if (ticket.getRow() > totalRows || ticket.getColumn() > totalColumns ||
                ticket.getRow() <= 0 || ticket.getColumn() <= 0) {
            throw new SeatNotFoundException();
        }

        for (Ticket s : availableTickets) {
            if (s.getRow() == ticket.getRow() && s.getColumn() == ticket.getColumn()) {
                availableTickets.remove(s);
                purchasedTickets.add(s);
                return s;
            }
        }

        throw new SeatPurchasedException();
    }

    public void returnFromPurchase(Ticket ticket) {
        for (Ticket s : purchasedTickets) {
            if (s.getRow() == ticket.getRow() && s.getColumn() == ticket.getColumn()) {
                availableTickets.add(ticket);
                purchasedTickets.remove(s);
                return;
            }
        }
    }

    public Map<String, Integer> calculateStatistic() {
        Map<String, Integer> res = new TreeMap<>();

        res.put("current_income", countCurrentIncome());
        res.put("number_of_available_seats", availableTickets.size());
        res.put("number_of_purchased_tickets", purchasedTickets.size());

        return res;
    }


    private Integer countCurrentIncome() {
        int total = 0;
        for (Ticket t : purchasedTickets) {
            total += t.getPrice();
        }
        return total;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public ArrayList<Ticket> getAvailableTickets() {
        return availableTickets;
    }
}
