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

    @GetMapping("/check_login")
    public Worker checkLogin(
            @RequestParam("login") String login,
            @RequestParam("password") String password
    ) {
        return workerService.checkLogin(login, password);
    }

    @GetMapping("/workers")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/storage_worker_by_department")
    public Worker getStorageWorkerByDepartment(@RequestParam Department department){
        return workerService.getStorageWorkerByDepartment(department);
    }

    @GetMapping("/workers_by_department")
    public List<Worker> getWorkersByDepartment(@RequestParam Department department){
        return workerService.getWorkersByDepartment(department);
    }

}
