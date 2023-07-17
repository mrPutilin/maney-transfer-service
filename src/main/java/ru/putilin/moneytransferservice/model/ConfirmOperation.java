package ru.putilin.moneytransferservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ConfirmOperation {


    @Size(min = 4, max = 4)
    @Value("1111")
    private String code;

    @NotBlank
    private String operationId;

    public ConfirmOperation() {
    }

    public ConfirmOperation(String code, String operationId) {
        this.code = code;
        this.operationId = operationId;
    }

    public ConfirmOperation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String verificationCode) {
        this.code = verificationCode;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "ConfirmOperation{" +
                "verificationCode='" + code + '\'' +
                ", operationId='" + operationId + '\'' +
                '}';
    }
}
