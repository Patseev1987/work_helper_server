package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findByLogin(String login);

    @Query("from Worker W where W.department =:department and W.type = 'STORAGE_WORKER'")
    Optional<Worker> findStorageWorkerByDepartment(Department department);

    @Query("from Worker W where W.department =:department and W.type = 'WORKER'")
    List<Worker> findAllWorkersByDepartment(Department department);
}
