package ru.putilin.moneytransferservice.controller;

import org.springframework.web.bind.annotation.*;
import ru.putilin.moneytransferservice.model.ConfirmOperation;
import ru.putilin.moneytransferservice.model.Operation;
import ru.putilin.moneytransferservice.model.SuccessConfirmation;
import ru.putilin.moneytransferservice.model.SuccessOperation;
import ru.putilin.moneytransferservice.service.TransferService;


@RestController
@CrossOrigin(origins = "https://serp-ya.github.io")
@RequestMapping("/")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }


    @PostMapping("transfer")
    public SuccessOperation transfer(@RequestBody Operation operation) {
        return service.transfer(operation);
    }


    @PostMapping("/confirmOperation")
    public SuccessConfirmation confirmOperation(@RequestBody ConfirmOperation confirmOperation) {
        return service.confirmOperation(confirmOperation);
    }


}
