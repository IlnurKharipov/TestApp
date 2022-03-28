package com.TestEDMS.test;

import com.TestEDMS.test.stateMachine.Events;
import com.TestEDMS.test.stateMachine.States;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	private StateMachine<States, Events> stateMachine;

	@Autowired
	private StateMachineFactory<States, Events> stateMachineFactory;

	@Before
	public void setUp() throws Exception {
		stateMachine = stateMachineFactory.getStateMachine();
	}
	@Test
	public void contextLoads() {
		Assertions.assertThat(stateMachine).isNotNull();
	}

	@Test
	public void initialStateTest() {
		// Asserts
		Assertions.assertThat(stateMachine.getInitialState().getId()).isEqualTo(States.PREPARATION);
	}
	@Test
	public void testGreenFlow() {
		// Arrange & Act
		stateMachine.sendEvent(Events.START_STEP);
		stateMachine.sendEvent(Events.STEP_FIRST_CONTROL);
		stateMachine.sendEvent(Events.SUCCESS_OF_CONTROL);
		// Asserts
		Assertions.assertThat(stateMachine.getState().getId())
				.isEqualTo(States.ACCEPTANCE);
	}
}