package me.d2o.tictactoe.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import me.d2o.tictactoe.config.Events;
import me.d2o.statemachine.StateMachineService;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.persistence.GameRepository;

@Component
public class InitializeGameListener {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private StateMachineService fsm;
	
	@EventListener
	protected void startupGame(SpringApplicationEvent event) {
		Game game = new Game();
		gameRepository.save(game);
		fsm.triggerTransition(game.getMachineId(), Events.INITIALIZE);
	}
}
