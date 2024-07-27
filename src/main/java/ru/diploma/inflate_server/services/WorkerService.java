package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.model.enums.Department;
import ru.diploma.inflate_server.model.enums.WorkerType;
import ru.diploma.inflate_server.repositories.WorkerRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public Worker addUser(Worker worker) {
        return workerRepository.save(worker);
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElseThrow();
    }

    public Worker getStorageWorkerByDepartment(Department department) {
        return workerRepository.findStorageWorkerByDepartment(department).orElseThrow();
    }


    public List<Worker> getWorkersByDepartment(Department department) {
        return workerRepository.findAllWorkersByDepartment(department);
    }

}
