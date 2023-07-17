package ru.putilin.moneytransferservice.advice;

public class ErrorInputData extends RuntimeException {

    public ErrorInputData(String msg) {
        super(msg);
    }

}
