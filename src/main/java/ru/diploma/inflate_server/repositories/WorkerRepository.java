package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.diploma.inflate_server.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
