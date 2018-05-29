package me.d2o.tictactoe.machinestates;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.d2o.statemachine.annotations.EnterMachineState;
import me.d2o.statemachine.annotations.ExitMachineState;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.service.GameService;
import me.d2o.tictactoe.service.InputService;

@Component
@Transactional
public class GameTurn {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private InputService inp;
	
	@EnterMachineState(States.TURN)
	public void enterState(MachineEvent event) {
		Game game = gameService.get(event.getMachineId());
		System.out.println("\nIt is Player "+(game.getTurn()+1)+"'s turn:");
		gameService.printBoard(game.getMachineId());
		char m = game.getTurn() == 0 ? 'x' : 'o';
		while (true){
			String[] cor = inp.getInput(game.getState()).split("");
			if (gameService.setPin(game.getMachineId(), cor[0], cor[1], m)){
				break;
			}
			System.out.println("Invalid coordinate!");
		}
		event.setPropagate(Events.PLAY);
	}

	@ExitMachineState(States.TURN)
	public void exitState(MachineEvent event) {
		Game game = gameService.get(event.getMachineId());
		char m = game.getTurn() == 0 ? 'x' : 'o';
		if (gameService.checkVictory(game.getMachineId(), m)){
			System.out.println(event.getExitState() + " "+event.getEnterState());
			if (event.getExitState().equals(event.getEnterState())){
				event.setPropagate(Events.FINALIZE);
				event.setTerminated(true);
			}
		} else {
			game.setTurn(game.getTurn() == 0 ? 1 : 0);
			event.setPropagate(Events.PLAY);
		}
	}

}
