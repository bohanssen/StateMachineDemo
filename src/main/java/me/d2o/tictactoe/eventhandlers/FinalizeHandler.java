/**
 *
 */
package me.d2o.tictactoe.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.d2o.tictactoe.config.Events;
import me.d2o.statemachine.MachineEvent;
import me.d2o.statemachine.MachineEventHandler;
import me.d2o.statemachine.StateMachineService;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.persistence.GameRepository;

@Service
@Transactional
public class FinalizeHandler extends MachineEventHandler {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private StateMachineService fsm;
	
	@Override
	public void handleEvent(MachineEvent event) {
		System.out.println("Game has ended!");
		
		//Start New game
		Game game = new Game();
		gameRepository.save(game);
		
		//And make sure the state machine is triggered
		fsm.triggerAsynchronousTransition(game.getMachineId(), Events.INITIALIZE);
	}

	@Override
	public String eventType() {
		return Events.FINALIZE;
	}

}
