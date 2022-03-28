package com.TestEDMS.test.repositories;

import com.TestEDMS.test.models.Company;
import com.TestEDMS.test.models.Documents;
import com.TestEDMS.test.models.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentsRepository extends CrudRepository<Documents, Long> {
    List<Documents> findBySubjectOfDocument(String documentsFilter);
    List<Documents> findByWorkersName(String workersName);
}
