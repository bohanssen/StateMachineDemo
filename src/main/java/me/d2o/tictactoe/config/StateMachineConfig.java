/**
 *
 */
package me.d2o.tictactoe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import me.d2o.statemachine.config.StateMachineConfigurable;

@Configuration
@ComponentScan("me.d2o.statemachine")
public class StateMachineConfig {

	@Bean
	public StateMachineConfigurable stateMachineConfigurable() {	
		StateMachineConfigurable fsm = new StateMachineConfigurable(Events.class,States.class);
		
		fsm.addTransition(Events.INITIALIZE, States.INITIAL, States.TURN);
		fsm.addTransition(Events.PLAY, States.TURN, States.TURN);
		fsm.addTransition(Events.FINALIZE, States.TURN, States.END);
		
		return fsm;
	}

}
