package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.Company;
import com.TestEDMS.test.models.Documents;
import com.TestEDMS.test.models.Subdivision;
import com.TestEDMS.test.models.Worker;
import com.TestEDMS.test.repositories.SubdivisionRepository;
import com.TestEDMS.test.repositories.WorkerRepository;
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
public class SubdivisionController {

    @Autowired
    private SubdivisionRepository subdivisionRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/subdivision")
    public String subdivisionMain(Model model){
        Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
        model.addAttribute("subdivisions", subdivisions);
        return "subdivision/subdivision";
    }
    @GetMapping("/subdivision/add")
    public String addSubdivision(Model model){
        return "subdivision/subdivision-add";
    }

    @PostMapping("/subdivision/add")
    public String subdivisionPostAdd(@RequestParam String companyName,@RequestParam String subdivisionName,
                                 @RequestParam String contactDetails, @RequestParam String subSupervisor,
                                 Model model){
        Subdivision subdivision = new Subdivision(companyName, subdivisionName, contactDetails,subSupervisor);
        subdivisionRepository.save(subdivision);
        return "redirect:/subdivision";
    }
    @GetMapping("/subdivision/{id}")
    public String subdivisionDetails(@PathVariable(value = "id") Long id, Model model){

        if(!subdivisionRepository.existsById(id)){
            return "redirect:/subdivision";
        }
        Optional<Subdivision> subdivision = subdivisionRepository.findById(id);
        List<Subdivision> result = new ArrayList<>();
        subdivision.ifPresent(result :: add);
        model.addAttribute("subdivision", result);
        return "subdivision/subdivision-details";
    }
    @GetMapping("/subdivision/{id}/edit")
    public String companyEdit(@PathVariable(value = "id") Long id, Model model) {

        if (!subdivisionRepository.existsById(id)) {
            return "redirect:/subdivision";
        }
        Optional<Subdivision> subdivision = subdivisionRepository.findById(id);
        List<Subdivision> result = new ArrayList<>();
        subdivision.ifPresent(result::add);
        model.addAttribute("subdivision", result);
        return "subdivision/subdivision-edit";
    }

    @PostMapping("/subdivision/{id}/edit")
    public String subdivisionPostUpdate(@PathVariable(value = "id") Long id,@RequestParam String companyName,
                                    @RequestParam String subdivisionName, @RequestParam String contactDetails,
                                    @RequestParam String subSupervisor, Model model){
        Subdivision subdivision = subdivisionRepository.findById(id).orElseThrow(IllegalStateException::new);
        subdivision.setCompanyName(companyName);
        subdivision.setSubdivisionName(subdivisionName);
        subdivision.setContactDetails(contactDetails);
        subdivision.setSubSupervisor(subSupervisor);
        subdivisionRepository.save(subdivision);
        return "redirect:/subdivision";
    }

    @PostMapping("/subdivision/{id}/remove")
    public String subdivisionPostDelete(@PathVariable(value = "id") Long id, Model model){
        Subdivision subdivision = subdivisionRepository.findById(id).orElseThrow(IllegalStateException::new);
        subdivisionRepository.delete(subdivision);
        return "redirect:/subdivision";
    }

    @PostMapping("/subFilter")
    public String filter(@RequestParam String subFilter, Model model){
        Iterable<Subdivision> subdivisionList;
        if(subFilter != null && !subFilter.isEmpty()) {
            subdivisionList = subdivisionRepository.findBySubdivisionName(subFilter);
        } else {
            subdivisionList = subdivisionRepository.findAll();
        }
        model.addAttribute("subdivisions", subdivisionList);
        return "subdivision/subdivision";
    }
    @GetMapping("/worker/special/{subdivisionName}")
    public String subdivisionWorker(@PathVariable(value = "subdivisionName") String subdivisionName, Model model){

        Iterable<Subdivision> subdivisions = subdivisionRepository.findBySubdivisionName(subdivisionName);
        Iterable<Worker> workers = workerRepository.findAll();
        List<Worker> workersTwo = new ArrayList<>();
        for(Subdivision subdivisionOne: subdivisions){
            for(Worker workerOne:workers){
                if(subdivisionOne.getSubdivisionName().equals(workerOne.getSubdivisionName())){
                    workersTwo.add(workerOne);
                }
            }
        }
        model.addAttribute("workers", workersTwo);
        return "subdivision/worker-special";
    }
}
