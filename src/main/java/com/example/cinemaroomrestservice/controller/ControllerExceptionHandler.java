package com.example.cinemaroomrestservice.controller;

import com.example.cinemaroomrestservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleSeatNotFound() {
        CustomErrorMessage body = new CustomErrorMessage("The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatPurchasedException.class)
    public ResponseEntity<CustomErrorMessage> handleTicketPurchased() {
        CustomErrorMessage body = new CustomErrorMessage("The ticket has been already purchased!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongToken() {
        CustomErrorMessage body = new CustomErrorMessage("Wrong token!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongPassword() {
        CustomErrorMessage body = new CustomErrorMessage("The password is wrong!");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}

