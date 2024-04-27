package ru.diploma.inflate_server.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.services.TransactionService;
import ru.diploma.inflate_server.webEntities.TransactionWEB;

import java.util.List;

import static ru.diploma.inflate_server.model.enums.Department.SHARPENING;
import static ru.diploma.inflate_server.model.enums.Department.STORAGE_OF_DECOMMISSIONED_TOOLS;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transaction/create")
    public Transaction createTransaction(@RequestBody TransactionWEB transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }


    @GetMapping("/transactions/worker")
    public List<Transaction> getTransactionsBySurname(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(value = "page",defaultValue = "0") Integer page
    ) {
        return transactionService.getTransactionsBySurnameSenderAndReceiver(workerId,page);
    }

    @GetMapping("/transactions/decommissionedTools")
    public List<Transaction> getDecommissionedTools(
            @RequestParam(name = "senderDepartment") Department senderDepartment,
            @RequestParam(name = "page",defaultValue = "0") Integer page
    ) {
        return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                senderDepartment, STORAGE_OF_DECOMMISSIONED_TOOLS,page
        );
    }

    @GetMapping("/transactions/toSharpen")
    public List<Transaction> getFromSharpen(
            @RequestParam(name = "senderDepartment") Department senderDepartment,
            @RequestParam(name = "page",defaultValue = "0") Integer page
    ) {
        return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                senderDepartment, SHARPENING,page
        );
    }

    @GetMapping("/transactions/fromSharpen")
    public List<Transaction> getToSharpen(
            @RequestParam(name = "receiverDepartment") Department receiverDepartment,
            @RequestParam(name = "page",defaultValue = "0") Integer page
    ) {
        return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                SHARPENING, receiverDepartment,page
        );
    }

}
