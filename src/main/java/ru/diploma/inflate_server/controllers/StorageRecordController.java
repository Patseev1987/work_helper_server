package ru.diploma.inflate_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;
import ru.diploma.inflate_server.auth.domain.Role;
import ru.diploma.inflate_server.auth.services.JwtTokenService;
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
    private final JwtTokenService jwtTokenService;

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

    @GetMapping ("/records/storageWorker/{toolCode}")
    public List<StorageRecord> getRecordsByToolCode(
            @PathVariable ("toolCode") String toolCode
    ){
        return storageRecordService.getAllRecordsWithTool(toolCode);
    }

    @GetMapping ("/records/worker/{toolCode}")
    public List<StorageRecord> getRecordsByToolCodeInDepartment(
            @PathVariable ("toolCode") String toolCode,
            @RequestHeader (name = HttpHeaders.AUTHORIZATION) String token
    ){
        System.out.println("!!!!!!!!!!!!**!**!*!*!**!*!*!*!*\n"+token);
        token = token.replace("Bearer ", "");
        String departmentValue = jwtTokenService.getDepartment(token);
        Department department = Department.valueOf(departmentValue);
        return storageRecordService.getAllRecordsWithToolType(department, toolCode);
    }
}
