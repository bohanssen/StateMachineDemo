/**
 *
 */
package me.d2o.tictactoe.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.eventhandler.MachineEventHandler;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.persistence.GameRepository;

@Service
@Transactional
public class NextTurnHandler extends MachineEventHandler {

	@Autowired
	private GameRepository gameRepository;
	
	@Override
	public void handleEvent(MachineEvent event) {
		Game game = gameRepository.findOne(event.getMachineId());
		char m = game.getState().equals(States.CHECK_PLAYER1) ? 'x' : 'o';
		if (game.checkVictory(m)){
			System.out.println(game.getState() + " has won the game!");
			game.printBoard();
			event.setPropagate(Events.FINALIZE);
		} else {
			event.setPropagate(Events.PLAY);
		}
	}

	@Override
	public String eventType() {
		return Events.NEXT_TURN;
	}

}
