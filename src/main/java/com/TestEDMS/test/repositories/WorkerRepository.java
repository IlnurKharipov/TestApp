package com.TestEDMS.test.repositories;

import com.TestEDMS.test.models.Company;
import com.TestEDMS.test.models.Subdivision;
import com.TestEDMS.test.models.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    List<Worker> findByWorkersName(String workFilter);
}
