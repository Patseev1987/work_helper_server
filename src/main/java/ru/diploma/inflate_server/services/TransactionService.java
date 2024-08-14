package ru.diploma.inflate_server.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.repositories.TransactionsRepository;

import java.time.LocalDate;
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

    private final ToolService toolService;
    private final WorkerService workerService;


     {
        init();
    }


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
                DEPARTMENT_19, SHARPENING, toolCode
        );
        var fromSharpen = getTransactionsBySenderDepartmentAndReceiverDepartment(
                SHARPENING, DEPARTMENT_19, toolCode
        );
        result.addAll(toSharpen);
        result.addAll(fromSharpen);

        return result.stream().sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).toList();
    }



    public void init() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Worker andrey = workerService.getWorkerById(1L);
                Worker ivan = workerService.getWorkerById(2L);
                Worker sergey = workerService.getWorkerById(3L);
                Worker storage = workerService.getWorkerById(4L);
                Worker fedor = workerService.getWorkerById(5L);
                Worker alex = workerService.getWorkerById(6L);
                Worker sharpen = workerService.getWorkerById(7L);
                Worker badTool = workerService.getWorkerById(9L);
//                VALUES (400, '2004-9060', 4),
//                        (500, '2004-1001', 4),
//                (200, '2004-10111', 4),
//                (1000, '2004-7480', 4),
//                (20, '8700-0001', 4),
//                (30, '8700-2001', 4),
//                (25, '8700-0987', 4),
//                (10, '6331-2222', 4),
//                (15, '6075-1331', 4),
//                (5, '6331-8065', 4);

                Tool cnmg = toolService.getToolByCodeLike("2004-9060").get(0);
                Tool vcmt = toolService.getToolByCodeLike("2004-1001").get(0);
                Tool gip = toolService.getToolByCodeLike("2004-10111").get(0);
                Tool dnmg = toolService.getToolByCodeLike("2004-7480").get(0);
                Tool shtangen1 = toolService.getToolByCodeLike("8700-0001").get(0);
                Tool cshtangen2 = toolService.getToolByCodeLike("8700-2001").get(0);
                Tool shtangen3 = toolService.getToolByCodeLike("8700-0987").get(0);
                Tool spec1 = toolService.getToolByCodeLike("6331-2222").get(0);
                Tool spec2 = toolService.getToolByCodeLike("6075-1331").get(0);
                Tool spec3 = toolService.getToolByCodeLike("6331-8065").get(0);

                Transaction tr1 = Transaction.builder()
                        .id(1L)
                        .amount(50)
                        .tool(cnmg)
                        .receiver(andrey)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();
                Transaction tr2 = Transaction.builder()
                        .id(2L)
                        .amount(20)
                        .tool(cnmg)
                        .receiver(sergey)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();
                Transaction tr3 = Transaction.builder()
                        .id(3L)
                        .amount(30)
                        .tool(cnmg)
                        .receiver(sharpen)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();
                Transaction tr4 = Transaction.builder()
                        .id(4L)
                        .amount(50)
                        .tool(cnmg)
                        .receiver(fedor)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();
                Transaction tr5 = Transaction.builder()
                        .id(5L)
                        .amount(20)
                        .tool(cnmg)
                        .receiver(storage)
                        .sender(fedor)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr6 = Transaction.builder()
                        .id(6L)
                        .amount(100)
                        .tool(dnmg)
                        .receiver(andrey)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();
                Transaction tr7 = Transaction.builder()
                        .id(7L)
                        .amount(80)
                        .tool(dnmg)
                        .receiver(storage)
                        .sender(andrey)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr8 = Transaction.builder()
                        .id(8L)
                        .amount(100)
                        .tool(dnmg)
                        .receiver(sharpen)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr9 = Transaction.builder()
                        .id(9L)
                        .amount(100)
                        .tool(dnmg)
                        .receiver(fedor)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr10 = Transaction.builder()
                        .id(10L)
                        .amount(100)
                        .tool(dnmg)
                        .receiver(storage)
                        .sender(sharpen)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr11 = Transaction.builder()
                        .id(11L)
                        .amount(100)
                        .tool(dnmg)
                        .receiver(badTool)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr12 = Transaction.builder()
                        .id(12L)
                        .amount(3)
                        .tool(shtangen1)
                        .receiver(andrey)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr13 = Transaction.builder()
                        .id(13L)
                        .amount(100)
                        .tool(vcmt)
                        .receiver(badTool)
                        .sender(storage)
                        .transactionDate(LocalDate.now())
                        .build();

                Transaction tr14 = Transaction.builder()
                        .id(14L)
                        .amount(2)
                        .tool(shtangen1)
                        .receiver(storage)
                        .sender(andrey)
                        .transactionDate(LocalDate.now())
                        .build();

                var transactions = List.of(tr1,tr2,tr3,tr4,tr5,tr6,tr7,tr8,tr9,tr10,tr11,tr12,tr13,tr14);

                transactions.forEach(this::createTransaction);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
