package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.ToolType;

import java.util.List;
import java.util.Optional;


public interface StorageRecordRepository extends JpaRepository<StorageRecord, Long> {

    List<StorageRecord> findAllByWorkerId(Long workerId);

    @Query("from StorageRecord S where S.worker.id =: workerID and S.tool.type =: toolType")
    List<StorageRecord> findAllByWorkerIdWithToolType(Long workerId, ToolType toolType);

    @Query("from StorageRecord S where S.worker.id =: workerID and S.tool.type =: toolType and S.tool.code like %:toolCode%")
    List<StorageRecord> findAllByWorkerIdWithToolTypeAndCode(Long workerId, ToolType toolType, String toolCode);

    @Query("from StorageRecord S where S.worker.lastName like %:workerLastname% and S.worker.department = :department")
    List<StorageRecord> findAllByWorkerLastname(Department department, String workerLastname);

    @Query("from StorageRecord S where S.worker = :worker and S.tool = :tool")
    Optional<StorageRecord> findStorageRecordByWorkerAndTool(Worker worker, Tool tool);

}
