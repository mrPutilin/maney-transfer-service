package ru.putilin.moneytransferservice.model;


public class Card {
    private String cardNumber;

    private String validDate;
    private String cvv;

    private Amount amount;

    public Card() {
    }

    public Card(String cardNumber, String validDate, String cvv, Amount amount) {
        this.cardNumber = cardNumber;
        this.validDate = validDate;
        this.cvv = cvv;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", validDate='" + validDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", amount=" + amount +
                '}';
    }
}
