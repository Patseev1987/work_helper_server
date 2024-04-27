package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.diploma.inflate_server.model.Tool;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool,String> {
@Query("from Tool T where T.code like %:code%")
    List<Tool> findAllByCodeLike(String code);
}
