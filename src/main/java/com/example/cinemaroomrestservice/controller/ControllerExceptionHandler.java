package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.exceptions.CustomErrorMessage;
import com.example.cinemaroomrestservice.exceptions.SeatNotFoundException;
import com.example.cinemaroomrestservice.exceptions.SeatPurchasedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleFlightNotFound(
            SeatNotFoundException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage("The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatPurchasedException.class)
    public ResponseEntity<CustomErrorMessage> handleFlightNotFound(
            SeatPurchasedException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage("The ticket has been already purchased!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}

