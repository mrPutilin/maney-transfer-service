package ru.putilin.moneytransferservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    private static int ID = 1;

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<Error> badRequest(ErrorInputData e) {
        return new ResponseEntity<>(new Error(e.getMessage(), ID++), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorTransfer.class)
    public ResponseEntity<Error> serverError(ErrorTransfer e) {
        return new ResponseEntity<>(new Error(e.getMessage(), ID++), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
