package ru.diploma.inflate_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diploma.inflate_server.model.StorageRecord;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.ToolType;
import ru.diploma.inflate_server.services.StorageRecordService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StorageRecordController {
    private final StorageRecordService storageRecordService;

    @GetMapping ("/records/workerId")
    public List<StorageRecord> getToolsByIdWorker(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolType") ToolType toolType,
            @RequestParam(name = "toolCode" , defaultValue = "") String toolCode
    ) {
        return storageRecordService.getStorageRecordsByWorkerIdWithParam(workerId, toolType, toolCode);
    }

    @GetMapping("/records/amount")
    public Integer getAmountByWorkerIdAndToolCode(
            @RequestParam(name = "workerId") Long workerId,
            @RequestParam(name = "toolCode") String toolCode
    ) {
        return storageRecordService.getAmountByWorkerIdAndToolCode(workerId,toolCode);
    }

    @GetMapping ("/records")
    public List<StorageRecord> getAllRecords() {
      return   storageRecordService.getAllRecords();
    }


    @GetMapping("/records/workers")
    public List<StorageRecord> getRecordsByWorkerLastName(
            @RequestParam(name = "workerLastName") String workerLastName,
            @RequestParam(name = "department") Department department
    ){
      return   storageRecordService.getAllStorageRecordsByWorkerLastName(
                department,
                workerLastName
        );
    }
}
