package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.Documents;
import com.TestEDMS.test.models.User;
import com.TestEDMS.test.repositories.DocumentsRepository;
import com.TestEDMS.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyDocumentsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DocumentsRepository documentsRepository;

    @GetMapping("/documents/mydocuments/{workersName}")
    public String myDocuments(@PathVariable(value = "workersName") String workersName, Model model){

        Iterable<User> user = userRepository.findByWorkersName(workersName);
        Iterable<Documents> documents = documentsRepository.findAll();
        List<Documents> documentsTwo = new ArrayList<>();
        for(User userOne: user){
            for(Documents documentsOne:documents){
                if(userOne.getWorkersName().equals(documentsOne.getWorkersName())){
                    documentsTwo.add(documentsOne);
                }
            }
        }
        model.addAttribute("myDocuments", documentsTwo);
        return "documents/my-documents";
    }
}
