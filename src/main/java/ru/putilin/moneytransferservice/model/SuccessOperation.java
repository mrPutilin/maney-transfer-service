package ru.putilin.moneytransferservice.model;

public class SuccessOperation {

    private String operationId;

    public SuccessOperation(String operationId) {
        this.operationId = operationId;
    }

    public SuccessOperation() {
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "SuccessOperation{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}
