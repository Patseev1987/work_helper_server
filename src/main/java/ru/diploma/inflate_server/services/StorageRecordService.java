package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.repositories.StorageRecordRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;

    public List<StorageRecord> getAllRecords() {
       return   storageRecordRepository.findAll();
    }

    public List<StorageRecord> getToolsByWorkerId(Long workerId) {
        return storageRecordRepository.findAll();
    }
}
