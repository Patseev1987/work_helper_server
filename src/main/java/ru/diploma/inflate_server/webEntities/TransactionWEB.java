package ru.diploma.inflate_server.webEntities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor

public class TransactionWEB {
    private Long id;
    private WorkerWEB sender;
    private WorkerWEB receiver;
    private Integer amount;
    private ToolWEB tool;
    private String transactionDate;
}
