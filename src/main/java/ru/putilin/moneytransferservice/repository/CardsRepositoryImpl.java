package ru.putilin.moneytransferservice.repository;


import org.springframework.stereotype.Repository;
import ru.putilin.moneytransferservice.model.Amount;
import ru.putilin.moneytransferservice.model.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CardsRepositoryImpl implements CardRepository {

    protected Map<String, Card> store = new HashMap<>();

    {
        store.put("1111222233334444", new Card("1111222233334444", "11/23", "345", new Amount(1000, "RUR")));

        store.put("1234567812345678", new Card("1234567812345678", "11/23", "782", new Amount(2000, "RUR")));
    }

    public CardsRepositoryImpl() {
    }

    public void setStore(HashMap<String, Card> store) {
        this.store = store;
    }

    public Map<String, Card> getStore() {
        return store;
    }

    @Override
    public Optional<Card> getCardData(String cardNumber) {
        return Optional.ofNullable(store.get(cardNumber));
    }



}
