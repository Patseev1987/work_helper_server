package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.repositories.TransactionsRepository;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionsRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionsRepository.save(transaction);
    }


}
