package com.TestEDMS.test.controllers;

import com.TestEDMS.test.repositories.DocumentsRepository;
import com.TestEDMS.test.stateMachine.Events;
import com.TestEDMS.test.stateMachine.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StateMachineController {
    int i = 0;

    @Autowired
    DocumentsRepository documentsRepository;

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    private StateMachine<States, Events> stateMachine;

    @GetMapping("/preparation")
    public String preparation(Model model) {
        model.addAttribute("preparation", "Стадия подготовки");
        i = 1;
        return "states/preparation";
    }

    @GetMapping("/execution")
    public String execution(Model model) {
        model.addAttribute("execution", "Стадия исполнения");
        i = 2;
        return "states/execution";
    }

    @GetMapping("/control")
    public String control(Model model) {
        model.addAttribute("control", "Стадия контроля");
        i = 3;
        return "states/control";
    }

    @GetMapping("/revision")
    public String revision(Model model) {
        model.addAttribute("revision", "Стадия доработки");
        i = 4;
        return "states/revision";
    }

    @GetMapping("/acceptance")
    public String acceptance(Model model) {
        model.addAttribute("acceptance", "Приём документа");
        i = 5;
        return "states/acceptance";
    }

    public void TestOfStateMachine() {
        stateMachine= stateMachineFactory.getStateMachine();
        if (i == 1) {
            stateMachine.sendEvent(Events.START_STEP);
        }
        if (i == 2) {
            stateMachine.sendEvent(Events.STEP_FIRST_CONTROL);
        }
        if (i == 3) {
            stateMachine.sendEvent(Events.FAIL_OF_CONTROL);
        }
        if (i == 4) {
            stateMachine.sendEvent(Events.STEP_SECOND_CONTROL);
        }
        if (i == 5) {
            stateMachine.sendEvent(Events.SUCCESS_OF_CONTROL);
        }
        if (stateMachine.getState().getId().equals(States.ACCEPTANCE)) {
            System.out.println("Success");

        }
    }

}
