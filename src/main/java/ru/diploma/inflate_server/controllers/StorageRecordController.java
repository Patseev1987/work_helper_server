package ru.diploma.inflate_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.services.StorageRecordService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StorageRecordController {
    private final StorageRecordService storageRecordService;

    @GetMapping ("/records/{workerId}")
    public List<StorageRecord> getToolsByIdWorker(@PathVariable Long workerId) {
        return storageRecordService.getToolsByWorkerId(workerId);
    }

    @GetMapping ("/records")
    public List<StorageRecord> getAllRecords() {
      return   storageRecordService.getAllRecords();
    }
}
