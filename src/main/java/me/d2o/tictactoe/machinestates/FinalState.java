package me.d2o.tictactoe.machinestates;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.core.StateMachineService;
import me.d2o.statemachine.eventhandler.MachineState;
import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.service.GameService;

@Component
@Transactional
public class FinalState extends MachineState {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private StateMachineService fsm;
	
	@Override
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

	@Override
	public void exitState(MachineEvent event) {
		// Nothing happens here
	}

	@Override
	public String state() {
		return States.END;
	}

	@Override
	@Order(75)
	@EventListener
	protected void enterListener(MachineEvent event) {
		// Override to alter priority Order: 51 -100 (Default: 100)
		// Lower Order is higher priority 
		super.enterListener(event);
	}

	@Override
	@Order(25)
	@EventListener
	protected void exitListener(MachineEvent event) {
		// Override to alter priority Order: 0 - 50 (Default: 50)
		// Lower Order is higher priority 
		super.exitListener(event);
	}

	
}
