package com.example.cinemaroomrestservice.exceptions;

public class CustomErrorMessage {

    private String error;

    public CustomErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
