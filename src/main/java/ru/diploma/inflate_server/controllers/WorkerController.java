package ru.diploma.inflate_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.diploma.inflate_server.auth.securityFilter.ErrorResponse;
import ru.diploma.inflate_server.exceptions.MyCustomException;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.services.WorkerService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService workerService;


    @GetMapping("/workers")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/storage_worker_by_department")
    public Worker getStorageWorkerByDepartment(@RequestParam(name = "department") Department department){
        return workerService.getStorageWorkerByDepartment(department);
    }

    @GetMapping("/workers_by_department")
    public List<Worker> getWorkersByDepartment(@RequestParam(name = "department") Department department){
        return workerService.getWorkersByDepartment(department);
    }

    @GetMapping("/worker_by_id/{id}")
    public Worker getWorkerById(@PathVariable(name = "id") Long id){
        return workerService.getWorkerById(id);
    }

}
