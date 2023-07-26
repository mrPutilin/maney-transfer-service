package ru.putilin.moneytransferservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.putilin.moneytransferservice.advice.ErrorInputData;
import ru.putilin.moneytransferservice.model.*;
import ru.putilin.moneytransferservice.repository.CardsRepositoryImpl;
import ru.putilin.moneytransferservice.repository.TransferRepositoryImpl;

import java.util.Objects;


@Service
public class TransferService {

    private final TransferRepositoryImpl transferRepository;
    private final CardsRepositoryImpl cardsRepository;
    public final ConfirmOperation confirmOperation;

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    public TransferService(TransferRepositoryImpl transferRepository, CardsRepositoryImpl cardsRepository,
                           ConfirmOperation confirmOperation) {
        this.cardsRepository = cardsRepository;
        this.transferRepository = transferRepository;
        this.confirmOperation = confirmOperation;
    }


    public SuccessOperation transfer(Operation operation) {
        confirmedCard(operation);
        confirmCvvOrDateCard(operation);
        checkMoney(operation);
        SuccessOperation successOperation = new SuccessOperation();
        successOperation.setOperationId(String.valueOf(transferRepository.adTransfer(operation)));
        return successOperation;

    }

    public SuccessConfirmation confirmOperation(ConfirmOperation confirmOperation) {
        confirmOperation.setOperationId(transferRepository.getIdOperation());
        confirmVerificationCode(confirmOperation.getCode());
        changCardsBalance(transferRepository.getIdOperation());
        return new SuccessConfirmation(confirmOperation.getOperationId());

    }

    public void confirmedCard(Operation operation) {
        if (!cardsRepository.getStore().containsKey(operation.getCardFromNumber()))
            throw new ErrorInputData("Карты с номером " + operation.getCardFromNumber() + " не существует");
        if (!cardsRepository.getStore().containsKey(operation.getCardToNumber()))
            throw new ErrorInputData("Карты с номером " + operation.getCardToNumber() + " не существует");
    }

    public void checkMoney(Operation operation) {
        if (cardsRepository.getStore().get(operation.getCardFromNumber()).getAmount().getValue() < operation.getAmount().getValue())
            throw new ErrorInputData("На карте " + operation.getCardFromNumber() + " не достаточно средств");
    }

    public String confirmVerificationCode(String verificationCode) {
        if (!verificationCode.equals(confirmOperation.getCode()))
            throw new ErrorInputData("Вы ввели не верный код");
        return verificationCode;
    }

    public void confirmCvvOrDateCard(Operation operation) {
        boolean validCvv = Objects.equals(operation.getCardFromCVV(), cardsRepository.getStore().get(operation.getCardFromNumber()).getCvv());
        boolean validDate = Objects.equals(operation.getCardFromValidTill(), cardsRepository.getStore().get(operation.getCardFromNumber()).getValidDate());
        if (!validCvv)
            throw new ErrorInputData("Не верный код cvv");
        if (!validDate) {
            throw new ErrorInputData("Не верная дата действия карты");
        }

    }

    public void changCardsBalance(String id) {

        Operation operation = transferRepository.getOperationById(id);

        int cardFromOldBalance = cardsRepository.getStore().get(operation.getCardFromNumber()).getAmount().getValue();
        int commission = (int) (operation.getAmount().getValue() * 0.01);
        int cardFromNewBalance = cardFromOldBalance - operation.getAmount().getValue() - commission;
        cardsRepository.getStore().get(operation.getCardFromNumber()).setAmount(new Amount(cardFromNewBalance, "rub"));

        int cardToOldBalance = cardsRepository.getStore().get(operation.getCardToNumber()).getAmount().getValue();
        int cardToNewBalance = cardToOldBalance + operation.getAmount().getValue();
        cardsRepository.getStore().get(operation.getCardToNumber()).setAmount(new Amount(cardToNewBalance, "rub"));

        logger.info("Успешно проведена операция перевода: " +
                        " С карты {} " +
                        " На карту {}" +
                        " Сумма операции {}" +
                        " Комиссия составила {}", operation.getCardFromNumber(), operation.getCardToNumber(),
                operation.getAmount(), commission);

    }

}