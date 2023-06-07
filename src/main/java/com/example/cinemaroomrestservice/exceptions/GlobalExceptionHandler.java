package com.example.cinemaroomrestservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSeatNotFound() {
        ExceptionResponse response = createErrorResponse(404, "The number of a row or a column is out of bounds!");
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(SeatPurchasedException.class)
    public ResponseEntity<ExceptionResponse> handleTicketPurchased() {
        ExceptionResponse response = createErrorResponse(404, "The ticket has been already purchased!");
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<ExceptionResponse> handleWrongToken() {
        ExceptionResponse response = createErrorResponse(404, "Wrong token!");
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ExceptionResponse> handleWrongPassword() {
        ExceptionResponse response = createErrorResponse(401, "The password is wrong!");
        return  ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    private ExceptionResponse createErrorResponse(int status, String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(status);
        exceptionResponse.setMessage(message);
        return exceptionResponse;
    }
}

