package ru.diploma.inflate_server.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.WorkerType;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "workerTable")
public class Worker {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private WorkerType type;
    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    private Department department;
}
