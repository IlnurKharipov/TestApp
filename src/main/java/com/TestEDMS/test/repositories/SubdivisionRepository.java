package com.TestEDMS.test.repositories;

import com.TestEDMS.test.models.Company;
import com.TestEDMS.test.models.Subdivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubdivisionRepository extends CrudRepository<Subdivision, Long> {
    List<Subdivision> findBySubdivisionName(String SubFilter);
    Optional<Subdivision> findByCompanyName(String companyName);
}
