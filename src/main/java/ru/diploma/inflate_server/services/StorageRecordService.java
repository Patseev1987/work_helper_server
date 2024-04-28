package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.ToolType;
import ru.diploma.inflate_server.model.enums.WorkerType;
import ru.diploma.inflate_server.repositories.StorageRecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;
    private static final String BLANK_CODE = "";

    public List<StorageRecord> getAllRecords() {
        return storageRecordRepository.findAll();
    }


    public List<StorageRecord> getStorageRecordsByWorkerIdWithParam(Long workerId, ToolType toolType, String toolCode) {
            return storageRecordRepository.findAllByWorkerIdWithToolTypeAndCode(workerId, toolType, toolCode);
    }

    public List<StorageRecord> getAllStorageRecordsByWorkerLastName(Department department, String lastName) {
        return storageRecordRepository.findAllByWorkerLastname(department, lastName);
    }

    public Optional<StorageRecord> getStorageRecordByWorkerAndTool(Worker worker, Tool tool) {
        return storageRecordRepository.findStorageRecordByWorkerAndTool(worker, tool);
    }

    public void save(StorageRecord storageRecord) {
        storageRecordRepository.save(storageRecord);
    }

    public Integer getAmountByWorkerIdAndToolCode(Long workerId, String toolCode) {
        var records = storageRecordRepository.findAllByWorkerId(workerId);
        records = records.stream()
                .filter(record -> record.getTool().getCode().equals(toolCode)).toList();
        if (records.isEmpty()) {
            return -1;
        } else {
            return records.get(0).getAmount();
        }
    }
}
