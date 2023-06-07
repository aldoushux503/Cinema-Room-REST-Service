package com.example.cinemaroomrestservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Token {
    private final String value;

    private Token(@JsonProperty("token") String value) {
        this.value = value;
    }

    public static Token generateToken() {
        String value = String.valueOf(UUID.randomUUID());
        return new Token(value);
    }

    public String getValue() {
        return value;
    }
}
