package ru.putilin.moneytransferservice.repository;

import ru.putilin.moneytransferservice.model.Card;

import java.util.Optional;

public interface CardRepository {
    Optional<Card> getCardData(String cardNumber);

}
