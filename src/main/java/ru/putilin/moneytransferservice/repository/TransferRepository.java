package ru.putilin.moneytransferservice.repository;

import ru.putilin.moneytransferservice.model.Operation;

public interface TransferRepository {

    Long adTransfer(Operation operation);
}
