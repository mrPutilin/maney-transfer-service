package ru.putilin.moneytransferservice;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.putilin.moneytransferservice.model.Amount;
import ru.putilin.moneytransferservice.model.Operation;
import ru.putilin.moneytransferservice.repository.TransferRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class TransferRepositoryImplTest {

    @Mock
    private Map<Long, Operation> storeOperationsMock;

    @InjectMocks
    private TransferRepositoryImpl transferRepository;


    @Test
    public void testAdTransferShouldReturnOperation() {
        Operation operation = new Operation("1111222233334444", "11/23", "345",
                "1234567812345678", new Amount(100, "rub"));
        Assertions.assertEquals(1, transferRepository.adTransfer(operation));

    }

    @Test
    public void testTransferShouldAddOperationInStore() {
        Map<Long, Operation> storeOperations = new HashMap<>();
        storeOperations.put(1L, new Operation());
        Mockito.when(storeOperationsMock.size()).thenReturn(storeOperations.size());
        Assertions.assertEquals(1, storeOperationsMock.size());
    }

    @Test
    public void testGetOperationByIdWhenStoreGetThenReturnOperation() {

        Operation operation = new Operation("1111222233334444", "11/23", "345",
                "1234567812345678", new Amount(100, "rub"));

        Mockito.when(storeOperationsMock.get(1L)).thenReturn(operation);
        Assertions.assertEquals(operation, transferRepository.getOperationById("1"));
    }

}
