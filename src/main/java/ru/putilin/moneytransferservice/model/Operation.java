package ru.putilin.moneytransferservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Operation {

    @Size(min = 16, max = 16)
    private String cardFromNumber;

    @Size(min = 4, max = 4)
    private String validTill;


    @Size(min = 3, max = 3)
    private String cvv;

    @Size(min = 16, max = 16)
    private String cardToNumber;


    @NotBlank
    private Amount amount;

    public Operation() {
    }

    public Operation(String cardFromNumber, String validTill, String cvv, String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.validTill = validTill;
        this.cvv = cvv;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", validTill='" + validTill + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(cardFromNumber, operation.cardFromNumber) && Objects.equals(validTill, operation.validTill) && Objects.equals(cvv, operation.cvv) && Objects.equals(cardToNumber, operation.cardToNumber) && Objects.equals(amount, operation.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromNumber, validTill, cvv, cardToNumber, amount);
    }
}
