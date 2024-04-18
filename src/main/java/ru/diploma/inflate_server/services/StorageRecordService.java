package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.repositories.StorageRecordRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;

    public List<StorageRecord> getAll() {
       return   storageRecordRepository.findAll();
    }
}
