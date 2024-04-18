package ru.diploma.inflate_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "storageRecordTable")
@IdClass(StorageRecordId.class)
public class StorageRecord {
    @Id
    @Column(name = "tool_code")
    private String toolCode;
    @Id
    @Column (name = "worker_id")
    private Long workerId;
    @Column (name = "amount", nullable = false)
    private Integer amount;
}
