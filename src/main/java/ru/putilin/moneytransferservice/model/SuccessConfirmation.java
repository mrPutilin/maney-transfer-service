package ru.putilin.moneytransferservice.model;


public class SuccessConfirmation {
    private String operationId;

    public SuccessConfirmation() {
    }

    public SuccessConfirmation(String operationId) {
        this.operationId = operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public String toString() {
        return "SuccessConfirmation{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}
