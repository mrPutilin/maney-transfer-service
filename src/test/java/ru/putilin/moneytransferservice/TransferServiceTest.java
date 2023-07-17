package ru.putilin.moneytransferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.putilin.moneytransferservice.advice.ErrorInputData;
import ru.putilin.moneytransferservice.model.Amount;
import ru.putilin.moneytransferservice.model.Card;
import ru.putilin.moneytransferservice.model.ConfirmOperation;
import ru.putilin.moneytransferservice.model.Operation;
import ru.putilin.moneytransferservice.repository.CardsRepositoryImpl;
import ru.putilin.moneytransferservice.repository.TransferRepositoryImpl;
import ru.putilin.moneytransferservice.service.TransferService;

import java.util.HashMap;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @Mock
    private TransferRepositoryImpl transferRepository;

    @Mock
    private CardsRepositoryImpl cardsRepository;


    @Mock
    public ConfirmOperation confirmOperatioN;

    private String verCode;

    @InjectMocks
    TransferService transferService;

    @BeforeEach
    public void setUp() {
        verCode = "1111";
    }


    @Test
    public void confirmVerificationCodTest() {
        Mockito.when(confirmOperatioN.getCode()).thenReturn("1111");
        transferService.confirmVerificationCode(verCode);
        Assertions.assertEquals(verCode, transferService.confirmVerificationCode(verCode));

    }

    @Test
    public void throwTheMessageFromException() {
        Mockito.when(confirmOperatioN.getCode()).thenReturn("2222");
        Exception a = Assertions.assertThrowsExactly(ErrorInputData.class, () -> transferService.confirmVerificationCode("1222"));
        Assertions.assertEquals("Вы ввели не верный код", a.getMessage());


    }

    @Test
    public void trowsExactlyException() {
        Mockito.when(confirmOperatioN.getCode()).thenReturn("2222");
        transferService.confirmVerificationCode("2222");
        Assertions.assertThrowsExactly(ErrorInputData.class, () -> transferService.confirmVerificationCode(verCode));

    }

    @Test
    public void testCheckMoneyWhenMoneyNotEnoughThenThrowException() {
        Operation operation = new Operation("1111222233334444", "11/23", "345",
                "1234567812345678", new Amount(1001, "rub"));
        Card card = new Card("1111222233334444", "11/23", "345", new Amount(1000, "rub"));
        Map<String, Card> store = new HashMap<>();
        store.put("1111222233334444", card);
        Mockito.when(cardsRepository.getStore()).thenReturn(store);
        Assertions.assertThrowsExactly(ErrorInputData.class, () -> transferService.checkMoney(operation));

    }
}
