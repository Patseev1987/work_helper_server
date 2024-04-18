package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diploma.inflate_server.model.Tool;

public interface ToolRepository extends JpaRepository<Tool,String> {
}
