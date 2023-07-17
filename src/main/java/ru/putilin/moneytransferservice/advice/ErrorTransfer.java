package ru.putilin.moneytransferservice.advice;

public class ErrorTransfer extends RuntimeException {

    public ErrorTransfer(String msg) {
        super(msg);
    }
}
