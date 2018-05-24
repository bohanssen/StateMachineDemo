package me.d2o.tictactoe.machinestates;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.eventhandler.MachineState;
import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;

@Component
@Transactional
public class InitialState extends MachineState {
	
	@Override
	public boolean enterCheck(MachineEvent event) {
		// Add a pre check
		// True makes sure the event gets handled
		// False will throw a Transition exception and the machine will not
		// Advance to the next state
		return true;
	}

	@Override
	public boolean exitCheck(MachineEvent event) {
		// Add a pre check
		// True makes sure the event gets handled
		// False will throw a Transition exception and the machine will not
		// Advance to the next state
		return true;
	}

	@Override
	public void enterState(MachineEvent event) {
		//The initial State will never trigger a enter event
	}

	@Override
	public void exitState(MachineEvent event) {
		// When the machine is started it will exit the initial state
		System.out.println("Welcome by a lame game called tic tac toe!");
		event.setPropagate(Events.PLAY);
	}

	@Override
	public String state() {
		return States.INITIAL;
	}

}
