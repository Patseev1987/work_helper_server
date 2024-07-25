package ru.diploma.inflate_server.auth.dto;




import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.WorkerType;

import java.time.LocalDate;

public record UserDTOForSingUp(
        String username,
        String password,
        String firstName,
        String lastName,
        String patronymic,
        WorkerType type,
        LocalDate joinDate,
        Department department) {
}
