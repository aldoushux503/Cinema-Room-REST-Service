package com.example.cinemaroomrestservice;

import com.example.cinemaroomrestservice.exceptions.SeatNotFoundException;
import com.example.cinemaroomrestservice.exceptions.SeatPurchasedException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class CinemaRoom {
    @JsonProperty("total_rows")
    private final int totalRows = 9;

    @JsonProperty("total_columns")
    private final int totalColumns = 9;

    @JsonProperty("available_seats")
    private final List<Ticket> availableTickets = Collections.synchronizedList(new ArrayList<>());

    @JsonIgnore
    private final List<Ticket> purchasedTickets = Collections.synchronizedList(new ArrayList<>());

    @JsonIgnore
    private final int PRICE_SWITCH = 4, PRICE_HIGH = 10, PRICE_LOW = 8;

    public CinemaRoom() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                availableTickets.add(new Ticket(row, column, row <= PRICE_SWITCH ? PRICE_HIGH : PRICE_LOW));
            }
        }
    }

    public Ticket findAvailableSeat(Ticket ticket) {
        if (ticket.row() > totalRows || ticket.column() > totalColumns ||
                ticket.row() <= 0 || ticket.column() <= 0) {
            throw new SeatNotFoundException();
        }

        for (Ticket s : availableTickets) {
            if (s.row() == ticket.row() && s.column() == ticket.column()) {
                availableTickets.remove(s);
                purchasedTickets.add(s);
                return s;
            }
        }

        throw new SeatPurchasedException();
    }

    public void returnFromPurchase(Ticket ticket) {
        for (Ticket s : purchasedTickets) {
            if (s.row() == ticket.row() && s.column() == ticket.column()) {
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
            total += t.price();
        }
        return total;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }
}
