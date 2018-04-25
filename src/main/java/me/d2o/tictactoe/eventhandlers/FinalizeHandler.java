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
import me.d2o.tictactoe.persistence.GameRepository;

@Service
@Transactional
public class FinalizeHandler extends MachineEventHandler {

	@Autowired
	private GameRepository gameRepository;
	
	@Override
	public void handleEvent(MachineEvent event) {
		System.out.println("Game has ended!");
		gameRepository.delete(event.getMachineId());
	}

	@Override
	public String eventType() {
		return Events.FINALIZE;
	}

}
