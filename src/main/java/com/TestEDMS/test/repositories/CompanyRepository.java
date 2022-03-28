package com.TestEDMS.test.repositories;

import com.TestEDMS.test.models.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long>{

    List<Company> findByCompanyName(String filter);

}