package com.TestEDMS.test.controllers;

import com.TestEDMS.test.models.Role;
import com.TestEDMS.test.models.Status;
import com.TestEDMS.test.models.User;
import com.TestEDMS.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationUsers(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUsers(@RequestParam String email, @RequestParam String password,
                                    @RequestParam String workersName, @RequestParam String workerSurname,
                                    Model model){
       User user = new User(email,password,workersName,workerSurname);
       user.setPassword(passwordEncoder.encode(password));
       user.setRole(Role.USER);
       user.setStatus(Status.ACTIVE);
       userRepository.save(user);
       return "redirect:/login";
    }
    @GetMapping("/instruction")
    public String getInstructions(){
        return "instruction";
    }
}
