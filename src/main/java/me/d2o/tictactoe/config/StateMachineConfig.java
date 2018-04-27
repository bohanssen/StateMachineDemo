/**
 *
 */
package me.d2o.tictactoe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import me.d2o.statemachine.StateMachineConfigurable;

@Configuration
@ComponentScan("me.d2o.statemachine")
public class StateMachineConfig {

	@Bean
	public StateMachineConfigurable stateMachineConfigurable() {	
		StateMachineConfigurable fsm = new StateMachineConfigurable(Events.class,States.class);
		
		fsm.addTransition(Events.INITIALIZE, States.INITIAL, States.PLAYER1);
		
		fsm.addTransition(Events.PLAY, States.PLAYER1, States.CHECK_PLAYER1);
		fsm.addTransition(Events.PLAY, States.PLAYER2, States.CHECK_PLAYER2);
		
		fsm.addTransition(Events.NEXT_TURN, States.CHECK_PLAYER1, States.PLAYER2);
		fsm.addTransition(Events.NEXT_TURN, States.CHECK_PLAYER2, States.PLAYER1);
		
		fsm.addTransition(Events.FINALIZE, States.PLAYER1, States.END);
		fsm.addTransition(Events.FINALIZE, States.PLAYER2, States.END);
		return fsm;
	}

}
