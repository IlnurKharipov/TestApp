package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.Company;
import com.TestEDMS.test.models.Subdivision;
import com.TestEDMS.test.repositories.CompanyRepository;
import com.TestEDMS.test.repositories.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @GetMapping("/company")
    public String companyMain(Model model){
        Iterable<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        return "company/company";
    }
    @GetMapping("/company/add")
    public String addCompany(Model model){
        return "company/company-add";
    }

    @PostMapping("/company/add")
    public String companyPostAdd(@RequestParam String companyName,@RequestParam String physicalAdress,
                                 @RequestParam String legalAdress, @RequestParam String comSupervisor,
                                 Model model){
        Company company = new Company(companyName, physicalAdress, legalAdress,comSupervisor);
        companyRepository.save(company);
        return "redirect:/company";
    }
    @GetMapping("/company/{id}")
    public String companyDetails(@PathVariable(value = "id") Long id, Model model){

        if(!companyRepository.existsById(id)){
            return "redirect:/company";
        }
       Optional<Company> company = companyRepository.findById(id);
        List<Company> result = new ArrayList<>();
        company.ifPresent(result :: add);
        model.addAttribute("company", result);
        return "company/company-details";
    }
    @GetMapping("/company/{id}/edit")
    public String companyEdit(@PathVariable(value = "id") Long id, Model model){

        if(!companyRepository.existsById(id)){
            return "redirect:/company";
        }
        Optional<Company> company = companyRepository.findById(id);
        List<Company> result = new ArrayList<>();
        company.ifPresent(result :: add);
        model.addAttribute("company", result);
        return "company/company-edit";
    }
    @PostMapping("/company/{id}/edit")
    public String companyPostUpdate(@PathVariable(value = "id") Long id,@RequestParam String companyName,
                                    @RequestParam String physicalAdress, @RequestParam String legalAdress,
                                    @RequestParam String comSupervisor, Model model){
        Company company = companyRepository.findById(id).orElseThrow(IllegalStateException::new);
        company.setCompanyName(companyName);
        company.setPhysicalAdress(physicalAdress);
        company.setLegalAdress(legalAdress);
        company.setComSupervisor(comSupervisor);
        companyRepository.save(company);
        return "redirect:/company";
    }
    @PostMapping("/company/{id}/remove")
    public String companyPostDelete(@PathVariable(value = "id") Long id, Model model){
        Company company = companyRepository.findById(id).orElseThrow(IllegalStateException::new);
        companyRepository.delete(company);
        return "redirect:/company";
    }
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model){
        Iterable<Company> companyList;
        if(filter != null && !filter.isEmpty()) {
            companyList = companyRepository.findByCompanyName(filter);
        } else {
            companyList = companyRepository.findAll();
        }
        model.addAttribute("companies", companyList);
        return "company/company";
    }

    @GetMapping("/subdivision/special/{companyName}")
    public String companySubdivision(@PathVariable(value = "companyName") String companyName, Model model){

        Iterable<Company> companies = companyRepository.findByCompanyName(companyName);
        Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
        List<Subdivision> subdivisionTwo = new ArrayList<>();
            for(Company companyOne: companies){
                for(Subdivision subdivisionOne:subdivisions){
                    if(companyOne.getCompanyName().equals(subdivisionOne.getCompanyName())){
                        subdivisionTwo.add(subdivisionOne);
                    }
                }
            }
        model.addAttribute("subdivisions", subdivisionTwo);
        return "company/subdivision-special";
    }
}
