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

//    public Worker checkLogin(String login, String password) {
//        var worker = workerRepository.findByLogin(login).orElse(wrongAnswerWorker());
//            if (worker.getPassword()!= null && worker.getPassword().equals(password)) {
//                return worker;
//        }
//        return wrongAnswerWorker();
//    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

//    private Worker wrongAnswerWorker(){
//        Worker worker = new Worker();
//        worker.setPassword("");
//        worker.setLogin("");
//        worker.setId(-1L);
//        worker.setFirstName("");
//        worker.setLastName("");
//        worker.setJoinDate(LocalDate.now());
//        worker.setDepartment(Department.DEPARTMENT_19);
//        worker.setType(WorkerType.WORKER);
//        worker.setPatronymic("");
//        return worker;
//    }

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
