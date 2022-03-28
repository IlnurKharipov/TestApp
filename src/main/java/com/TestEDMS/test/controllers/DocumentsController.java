package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.Documents;
import com.TestEDMS.test.repositories.DocumentsRepository;
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
public class DocumentsController {

    @Autowired
    private DocumentsRepository documentsRepository;

    @GetMapping("/documents")
    public String documentsMain(Model model){
        Iterable<Documents> documents = documentsRepository.findAll();
        model.addAttribute("documents", documents);
        return "documents/documents";
    }
    @GetMapping("/documents/add")
    public String addDocuments(){
        return "documents/documents-add";
    }

    @PostMapping("/documents/add")
    public String documentsPostAdd(@RequestParam String workersName,@RequestParam String subjectOfDocument,
                                     @RequestParam String authorOfDocument, @RequestParam String executorsOfDocument,
                                     @RequestParam String periodOfExecution, @RequestParam String signOfControl,
                                     @RequestParam String signOfPerformance, @RequestParam String textOfDocument,
                                     Model model){
        Documents documents = new Documents(workersName, subjectOfDocument, authorOfDocument, executorsOfDocument,
                                    periodOfExecution,signOfControl, signOfPerformance, textOfDocument);
        documentsRepository.save(documents);
        return "redirect:/documents";
    }
    @GetMapping("/documents/{id}")
    public String documentsDetails(@PathVariable(value = "id") Long id, Model model){

        if(!documentsRepository.existsById(id)){
            return "redirect:/documents";
        }
        Optional<Documents> documents = documentsRepository.findById(id);
        List<Documents> result = new ArrayList<>();
        documents.ifPresent(result :: add);
        model.addAttribute("documents", result);
        return "documents/documents-details";
    }
    @GetMapping("/documents/{id}/edit")
    public String documentsEdit(@PathVariable(value = "id") Long id, Model model) {

        if (!documentsRepository.existsById(id)) {
            return "redirect:/documents";
        }
        Optional<Documents> documents = documentsRepository.findById(id);
        List<Documents> result = new ArrayList<>();
        documents.ifPresent(result::add);
        model.addAttribute("documents", result);
        return "documents/documents-edit";
    }

    @PostMapping("/documents/{id}/edit")
    public String documentsPostUpdate(@PathVariable(value = "id") Long id, @RequestParam String workersName,
                                      @RequestParam String subjectOfDocument, @RequestParam String authorOfDocument,
                                      @RequestParam String executorsOfDocument, @RequestParam String periodOfExecution,
                                      @RequestParam String signOfControl, @RequestParam String signOfPerformance,
                                      @RequestParam String textOfDocument, Model model){
        Documents documents = documentsRepository.findById(id).orElseThrow(IllegalStateException::new);
        documents.setWorkersName(workersName);
        documents.setSubjectOfDocument(subjectOfDocument);
        documents.setAuthorOfDocument(authorOfDocument);
        documents.setExecutorsOfDocument(executorsOfDocument);
        documents.setPeriodOfExecution(periodOfExecution);
        documents.setSignOfControl(signOfControl);
        documents.setSignOfPerformance(signOfPerformance);
        documents.setTextOfDocument(textOfDocument);
        documentsRepository.save(documents);
        return "redirect:/documents";
    }

    @PostMapping("/documents/{id}/remove")
    public String documentsPostDelete(@PathVariable(value = "id") Long id, Model model){
        Documents documents = documentsRepository.findById(id).orElseThrow(IllegalStateException::new);
        documentsRepository.delete(documents);
        return "redirect:/documents";
    }

    @PostMapping("/documentsFilter")
    public String documentsFilter(@RequestParam String documentsFilter, Model model){
        Iterable<Documents> documentsList;
        if(documentsFilter != null && !documentsFilter.isEmpty()) {
            documentsList = documentsRepository.findBySubjectOfDocument(documentsFilter);
        } else {
            documentsList = documentsRepository.findAll();
        }
        model.addAttribute("documents", documentsList);
        return "documents/documents";
    }
}
