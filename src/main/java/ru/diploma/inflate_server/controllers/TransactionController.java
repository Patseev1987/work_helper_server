package ru.diploma.inflate_server.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.services.TransactionService;
import ru.diploma.inflate_server.webEntities.TransactionWEB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.diploma.inflate_server.model.enums.Department.*;

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

    @GetMapping("/transactions/actionWithAnotherDepartments")
    public List<Transaction> getDecommissionedTools(
            @RequestParam(name = "anotherDepartment") Department anotherDepartment,
            @RequestParam(name = "toolCode", defaultValue = "") String toolCode
    ) {
        if (anotherDepartment == STORAGE_OF_DECOMMISSIONED_TOOLS) {
            return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                    DEPARTMENT_19, STORAGE_OF_DECOMMISSIONED_TOOLS,toolCode
            );
        } else if (anotherDepartment == SHARPENING) {
            return transactionService.getTransactionsWithSharpening(toolCode);
        }else {
            return transactionService.getTransactionsBySenderDepartmentAndReceiverDepartment(
                    MAIN_STORAGE, DEPARTMENT_19,toolCode
            );
        }
    }
}
