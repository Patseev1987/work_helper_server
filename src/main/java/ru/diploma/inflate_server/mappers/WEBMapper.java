package ru.diploma.inflate_server.mappers;

import org.springframework.stereotype.Component;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.model.Transaction;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.webEntities.ToolWEB;
import ru.diploma.inflate_server.webEntities.TransactionWEB;
import ru.diploma.inflate_server.webEntities.WorkerWEB;

import java.time.LocalDate;
@Component
public class WEBMapper {
    public Tool toolWEBToTool (ToolWEB toolWEB ){
        Tool tool = new Tool();
        tool.setIcon(toolWEB.getIcon());
        tool.setName(toolWEB.getName());
        tool.setDescription(toolWEB.getDescription());
        tool.setType(toolWEB.getType());
        tool.setAdditionalInfo(toolWEB.getAdditionalInfo());
        tool.setPlace(toolWEB.getPlace());
        tool.setCode(toolWEB.getCode());
        return tool;
    }

    public Worker workerWEBToWorker(WorkerWEB workerWEB ){
        Worker worker = new Worker();
        worker.setPatronymic(workerWEB.getPatronymic());
        worker.setId(workerWEB.getId());
        worker.setDepartment(workerWEB.getDepartment());
        worker.setType(workerWEB.getType());
        worker.setFirstName(workerWEB.getFirstName());
        worker.setLastName(workerWEB.getLastName());
        worker.setLogin(workerWEB.getLogin());
        worker.setPassword(workerWEB.getPassword());
        if (!workerWEB.getJoinDate().equals("null")){
            worker.setJoinDate(LocalDate.parse(workerWEB.getJoinDate()));
        }
        return worker;
    }

    public Transaction transactionWEBToTransaction(TransactionWEB transactionWEB ){
        Transaction transaction = new Transaction();
        transaction.setId(transactionWEB.getId());
        transaction.setReceiver(
               workerWEBToWorker(transactionWEB.getReceiver())
        );
        transaction.setSender(
                workerWEBToWorker(transactionWEB.getSender())
        );
        transaction.setAmount(transactionWEB.getAmount());
        transaction.setTool(
                toolWEBToTool(transactionWEB.getTool())
        );
        if (!transactionWEB.getTransactionDate().equals("null")) {
            transaction.setTransactionDate(LocalDate.parse(transactionWEB.getTransactionDate()));
        }else {
            transaction.setTransactionDate(LocalDate.now());
        }

        return transaction;
    }


}
