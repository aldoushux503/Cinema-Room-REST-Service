package com.example.cinemaroomrestservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    private String value;

    public Token(@JsonProperty("token") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
