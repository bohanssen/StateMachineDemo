package me.d2o.tictactoe.machinestates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.d2o.statemachine.annotations.EnterMachineState;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.core.StateMachineService;
import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.service.GameService;

@Component
public class FinalState {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private StateMachineService fsm;
	
	@EnterMachineState(States.END)
	public void enterState(MachineEvent event) {
		System.out.println("Game has ended!");
		
		System.out.println("Player " + (gameService.get(event.getMachineId()).getTurn()+1) + " has won the game!");
		gameService.printBoard(event.getMachineId());
		
		//Start New game
		Game game = gameService.initializeNew();
		
		//Wait three seconds and then make sure the state machine is triggered
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fsm.triggerAsynchronousTransition(game.getMachineId(), Events.INITIALIZE);
	}
}
