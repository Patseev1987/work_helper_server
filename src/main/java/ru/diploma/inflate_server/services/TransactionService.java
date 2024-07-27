package ru.diploma.inflate_server.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.repositories.TransactionsRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.diploma.inflate_server.model.enums.Department.DEPARTMENT_19;
import static ru.diploma.inflate_server.model.enums.Department.SHARPENING;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final StorageRecordService storageRecordService;

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        changeStorageRecord(transaction);
        return transactionsRepository.save(transaction);
    }


    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Transactional
    void changeStorageRecord(Transaction transaction) {
        var senderStorageRecord = storageRecordService.getStorageRecordByWorkerAndTool(
                transaction.getSender(),
                transaction.getTool()
        );
        var receiverStorageRecord = storageRecordService.getStorageRecordByWorkerAndTool(
                transaction.getReceiver(),
                transaction.getTool()
        );

        if (senderStorageRecord.isPresent()) {
            int newValue = senderStorageRecord.get().getAmount() - transaction.getAmount();
            senderStorageRecord.get().setAmount(newValue);
            if (newValue < 0) {
                throw new RuntimeException("Negative amount not allowed");
            }

            storageRecordService.save(senderStorageRecord.get());
        } else {
            throw new RuntimeException("sender storage record not found");
        }
        if (receiverStorageRecord.isPresent()) {
            int newValue = receiverStorageRecord.get().getAmount() + transaction.getAmount();
            receiverStorageRecord.get().setAmount(newValue);
            storageRecordService.save(receiverStorageRecord.get());
        } else {
            var newStorageRecord = new StorageRecord();
            newStorageRecord.setAmount(transaction.getAmount());
            newStorageRecord.setTool(transaction.getTool());
            newStorageRecord.setWorker(transaction.getReceiver());
            storageRecordService.save(newStorageRecord);
        }
    }

    public List<Transaction> getTransactionsBySurnameSenderAndReceiver(Long workerId, Integer page) {
        Integer offset = page * 200;
        return transactionsRepository.findTransactionsBySurnameSenderAndReceiver(workerId, offset);
    }

    public List<Transaction> getTransactionsBySenderDepartmentAndReceiverDepartment(Department senderDepartment,
                                                                                    Department receiverDepartment,
                                                                                    String toolCode) {
        return transactionsRepository
                .findAllTransactionsBySenderDepartmentAndReceiverDepartment(senderDepartment,
                        receiverDepartment,
                        toolCode);
    }

    public List<Transaction> getTransactionsWithSharpening(String toolCode) {
        var result = new ArrayList<Transaction>();
        var toSharpen = getTransactionsBySenderDepartmentAndReceiverDepartment(
                DEPARTMENT_19, SHARPENING,toolCode
        );
        var fromSharpen = getTransactionsBySenderDepartmentAndReceiverDepartment(
                SHARPENING, DEPARTMENT_19,toolCode
        );
        result.addAll(toSharpen);
        result.addAll(fromSharpen);

        return result.stream().sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).toList();
    }

}
