package ru.diploma.inflate_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diploma.inflate_server.model.StorageRecord;


public interface StorageRecordRepository extends JpaRepository<StorageRecord,Long> {

}
