package ru.putilin.moneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.putilin.moneytransferservice.model.SuccessOperation;
import ru.putilin.moneytransferservice.model.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransferRepositoryImpl implements TransferRepository {


    private final  Map<Long, Operation> storeOperations;
    private final AtomicLong idOperation = new AtomicLong(0);
    private final SuccessOperation successOperation = new SuccessOperation();

    public TransferRepositoryImpl() {
        storeOperations = new HashMap<>();
    }

    public TransferRepositoryImpl(Map<Long, Operation> storeOperations) {
        this.storeOperations = storeOperations;
    }

    @Override
    public Long adTransfer(Operation operation) {
        Long id = idOperation.incrementAndGet();
        storeOperations.put(id, operation);
        return id;
    }

    public Operation getOperationById(String id) {
        return storeOperations.get(Long.parseLong(id));
    }

    public String getIdOperation() {
        return String.valueOf(idOperation);
    }

    public Map<Long, Operation> getStoreOperations() {
        return storeOperations;
    }







}
