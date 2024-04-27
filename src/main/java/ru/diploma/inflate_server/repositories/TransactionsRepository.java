package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.enums.Department;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query("from Transaction T where T.sender.id = :workerId or T.receiver.id =:workerId order by date(T.transactionDate) desc limit 100 offset :offset ")
    List<Transaction> findTransactionsBySurnameSenderAndReceiver(Long workerId, Integer offset);

    @Query("from Transaction  T where T.sender.department = :senderDepartment and T.receiver.department =:receiverDepartment order by date(T.transactionDate) desc limit 60 offset :offset ")
    List<Transaction> findAllTransactionsBySenderDepartmentAndReceiverDepartment(Department senderDepartment, Department receiverDepartment,Integer offset);
}
