package ru.diploma.inflate_server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.repositories.WorkerRepository;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }

}
