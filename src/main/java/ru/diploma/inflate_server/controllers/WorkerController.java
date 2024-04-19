package ru.diploma.inflate_server.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.diploma.inflate_server.model.Worker;
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
}
