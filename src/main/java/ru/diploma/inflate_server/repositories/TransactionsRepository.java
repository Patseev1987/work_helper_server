package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diploma.inflate_server.model.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
}
