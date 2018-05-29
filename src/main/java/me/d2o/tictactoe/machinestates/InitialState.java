package me.d2o.tictactoe.machinestates;

import org.springframework.stereotype.Component;

import me.d2o.statemachine.annotations.ExitMachineState;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;

@Component
public class InitialState  {

	@ExitMachineState(States.INITIAL)
	public void exitState(MachineEvent event) {
		// When the machine is started it will exit the initial state
		System.out.println("Welcome by a lame game called tic tac toe!");
		event.setPropagate(Events.PLAY);
	}

}
