package ru.diploma.inflate_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table( name = "transactions_table")
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Worker sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Worker receiver;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "tool_code")
    private Tool tool;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
}
