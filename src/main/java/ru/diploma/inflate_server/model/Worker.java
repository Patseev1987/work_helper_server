package ru.diploma.inflate_server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.WorkerType;
import ru.diploma.inflate_server.utils.LocalDateDeserializer;
import ru.diploma.inflate_server.utils.LocalDateSerializer;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@Table(name = "workers_table")
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private WorkerType type;
    @Column(name = "join_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;
    @Column(name = "login")
    private String login;
}
