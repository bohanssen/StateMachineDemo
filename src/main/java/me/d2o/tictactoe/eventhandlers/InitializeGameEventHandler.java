/**
 *
 */
package me.d2o.tictactoe.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.d2o.tictactoe.config.Events;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.eventhandler.MachineEventHandler;
import me.d2o.tictactoe.service.GameService;

@Service
@Transactional
public class InitializeGameEventHandler extends MachineEventHandler {

	@Autowired
	private GameService gameService;
	
	@Override
	public void handleEvent(MachineEvent event) {
		System.out.println("Welcome by a lame game called tic tac toe!");
		gameService.printBoard(event.getMachineId());
		event.setPropagate(Events.PLAY);
	}

	@Override
	public String eventType() {
		return Events.INITIALIZE;
	}

}
