package ru.putilin.moneytransferservice.advice;

public class Error {
    private String message;
    private int id;


    public Error() {
    }

    public Error(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return id + " " + message;
    }
}
