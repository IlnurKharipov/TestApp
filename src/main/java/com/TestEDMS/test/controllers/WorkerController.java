package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.Documents;
import com.TestEDMS.test.models.Worker;
import com.TestEDMS.test.repositories.DocumentsRepository;
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
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private DocumentsRepository documentsRepository;

    @GetMapping("/worker")
    public String workerMain(Model model){
        Iterable<Worker> workers = workerRepository.findAll();
        model.addAttribute("workers", workers);
        return "worker/worker";
    }
    @GetMapping("/worker/add")
    public String addWorker(Model model){
        return "worker/worker-add";
    }

    @PostMapping("/worker/add")
    public String workerPostAdd(@RequestParam String subdivisionName,@RequestParam String workersName,
                                @RequestParam String workerSurname, @RequestParam String workerMiddleName,
                                @RequestParam String workerPosition, Model model){
        Worker worker= new Worker(subdivisionName, workersName, workerSurname, workerMiddleName, workerPosition);
        workerRepository.save(worker);
        return "redirect:/worker";
    }

    @GetMapping("/worker/{id}")
    public String workerDetails(@PathVariable(value = "id") Long id, Model model){

        if(!workerRepository.existsById(id)){
            return "redirect:/worker";
        }
        Optional<Worker> worker = workerRepository.findById(id);
        List<Worker> result = new ArrayList<>();
        worker.ifPresent(result :: add);
        model.addAttribute("worker", result);
        return "worker/worker-details";
    }
    @GetMapping("/worker/{id}/edit")
    public String workerEdit(@PathVariable(value = "id") Long id, Model model) {

        if (!workerRepository.existsById(id)) {
            return "redirect:/worker";
        }
        Optional<Worker> worker = workerRepository.findById(id);
        List<Worker> result = new ArrayList<>();
        worker.ifPresent(result::add);
        model.addAttribute("worker", result);
        return "worker/worker-edit";
    }

    @PostMapping("/worker/{id}/edit")
    public String workerPostUpdate(@PathVariable(value = "id") Long id,@RequestParam String subdivisionName,
                                        @RequestParam String workersName, @RequestParam String workerSurname,
                                        @RequestParam String workerMiddleName, @RequestParam String workerPosition,
                                        Model model){
        Worker worker = workerRepository.findById(id).orElseThrow(IllegalStateException::new);
        worker.setSubdivisionName(subdivisionName);
        worker.setWorkersName(workersName);
        worker.setWorkerSurname(workerSurname);
        worker.setWorkerMiddleName(workerMiddleName);
        worker.setWorkerPosition(workerPosition);
        workerRepository.save(worker);
        return "redirect:/worker";
    }
    @PostMapping("/worker/{id}/remove")
    public String workerPostDelete(@PathVariable(value = "id") Long id, Model model){
        Worker worker = workerRepository.findById(id).orElseThrow(IllegalStateException::new);
        workerRepository.delete(worker);
        return "redirect:/worker";
    }

    @PostMapping("/workFilter")
    public String workFilter(@RequestParam String workFilter, Model model){
        Iterable<Worker> workersList;
        if(workFilter != null && !workFilter.isEmpty()) {
            workersList = workerRepository.findByWorkersName(workFilter);
        } else {
            workersList = workerRepository.findAll();
        }
        model.addAttribute("workers", workersList);
        return "worker/worker";
    }
    @GetMapping("/documents/special/{workersName}")
    public String workerDocuments(@PathVariable(value = "workersName") String workersName, Model model){

        Iterable<Worker> workers = workerRepository.findByWorkersName(workersName);
        Iterable<Documents> documents = documentsRepository.findAll();
        List<Documents> documentsTwo = new ArrayList<>();
        for(Worker workerOne: workers){
            for(Documents documentsOne :documents){
                if(workerOne.getWorkersName().equals(documentsOne.getWorkersName())){
                    documentsTwo.add(documentsOne);
                }
            }
        }
        model.addAttribute("documents", documentsTwo);
        return "worker/documents-special";
    }
}