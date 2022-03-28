package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.User;
import com.TestEDMS.test.repositories.DocumentsRepository;
import com.TestEDMS.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DocumentsRepository documentsRepository;

    @GetMapping("/my-page")
    public String myPage(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        for(User user: users){
            if(user.getEmail().equals(username)){
                userList.add(user);
            }
        }
        model.addAttribute("user", userList);
        return "my-page/my-page";
    }
}
