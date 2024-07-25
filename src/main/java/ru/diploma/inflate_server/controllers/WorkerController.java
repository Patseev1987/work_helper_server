package ru.diploma.inflate_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.services.WorkerService;

import java.util.List;

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

}
