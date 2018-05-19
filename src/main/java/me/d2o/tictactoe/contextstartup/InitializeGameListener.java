package me.d2o.tictactoe.contextstartup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import me.d2o.tictactoe.config.Events;
import me.d2o.statemachine.core.StateMachineService;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.service.GameService;

@Component
public class InitializeGameListener {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private StateMachineService fsm;
	
	private boolean init = true;
	
	@EventListener
	protected void startupGame(SpringApplicationEvent event) {
		if (init){
			init = false;
			Game game = gameService.initializeNew();
			fsm.triggerTransition(game.getMachineId(), Events.INITIALIZE);
		}
	}
}
