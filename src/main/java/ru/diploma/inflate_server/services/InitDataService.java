package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.repositories.TransactionsRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitDataService {
    private final  ToolService toolService;
    private final  WorkerService workerService;
    private final TransactionsRepository transactionService;



}
