package ru.diploma.inflate_server.webEntities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.WorkerType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkerWEB {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private WorkerType type;
    private String joinDate;
    private Department department;
    private String login;
    private String password;
}
