package com.TestEDMS.test.config;

import com.TestEDMS.test.stateMachine.Events;
import com.TestEDMS.test.stateMachine.States;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

@Slf4j
@Configuration
@EnableStateMachineFactory

class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    static final Logger log = LoggerFactory.getLogger(StateMachineConfig.class);

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {

        config.withConfiguration()
                .listener(listener())
                .autoStartup(true);
    }

    private StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>(){
            @Override
            public void transition(Transition<States, Events> transition) {
                log.warn("move from:{} to:{}",
                        ofNullableState(transition.getSource()),
                        ofNullableState(transition.getTarget()));
            }

            @Override
            public void eventNotAccepted(Message<Events> event) {
                log.error("event not accepted: {}", event);
            }

            private Object ofNullableState(State s) {
                return Optional.ofNullable(s)
                        .map(State::getId)
                        .orElse(null);
            }
        };
    }


    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {

        states.withStates()
                .initial(States.PREPARATION, deployAction1())
                .state(States.EXECUTION)
                .state(States.CONTROL, deployAction())
                .state(States.REVISION, deployAction2())
                .end(States.ACCEPTANCE);
    }

    private Action<States, Events> deployAction2() {
        return stateContext -> log.info("Всё прошло успешно");
    }

    private Action<States, Events> deployAction1() {
        return stateContext -> log.info("Доппроверка");
    }

    private Action<States, Events> deployAction() {
        return stateContext -> log.warn("Проверка логгера");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {

        transitions.withExternal()
                .source(States.PREPARATION)
                .target(States.EXECUTION)
                .event(Events.START_STEP)
                .and()
                .withExternal()
                .source(States.EXECUTION)
                .target(States.CONTROL)
                .event(Events.STEP_FIRST_CONTROL)
                .and()
                .withExternal()
                .source(States.CONTROL)
                .target(States.ACCEPTANCE)
                .event(Events.SUCCESS_OF_CONTROL)
                .and()
                .withExternal()
                .source(States.CONTROL)
                .target(States.REVISION)
                .event(Events.FAIL_OF_CONTROL)
                .and()
                .withExternal()
                .source(States.REVISION)
                .target(States.EXECUTION)
                .event(Events.STEP_SECOND_CONTROL);
    }
}
