/**
 *
 */
package me.d2o.tictactoe.eventhandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.d2o.tictactoe.config.Events;
import me.d2o.tictactoe.config.States;
import me.d2o.statemachine.core.MachineEvent;
import me.d2o.statemachine.eventhandler.MachineEventHandler;
import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.service.GameService;

@Service
@Transactional
public class PlayHandler extends MachineEventHandler {
	
	@Autowired
	private GameService gameService;

	private InputStreamReader in;
	private BufferedReader bufferRead;
	
	@PostConstruct
	private void init(){
		in = new InputStreamReader(System.in);
		bufferRead = new BufferedReader(in);
	}
	
	@PreDestroy
	private void destroy() throws IOException{
		bufferRead.close();
		in.close();
	}
	
	private String getInput(String player){
		while (true){
			try{
				System.out.println(player+ " please input coordinates {A1}:");
				String inputstring = bufferRead.readLine().trim();
				Pattern p = Pattern.compile("[ABCabc..][123..]");
				Matcher m = p.matcher(inputstring);
				if (m.find()){
					return inputstring;
				} 
			} catch(Exception ex){

			}
		}
	}
	
	@Override
	public void handleEvent(MachineEvent event) {
		Game game = gameService.get(event.getMachineId());
		System.out.println("\nIt is "+game.getState()+"'s turn:");
		gameService.printBoard(game.getMachineId());
		char m = game.getState().equals(States.PLAYER1) ? 'x' : 'o';
		while (true){
			String[] cor = getInput(game.getState()).split("");
			if (gameService.setPin(game.getMachineId(), cor[0], cor[1], m)){
				break;
			}
			System.out.println("Invalid coordinate!");
		}
		event.setPropagate(Events.NEXT_TURN);
	}

	@Override
	public String eventType() {
		return Events.PLAY;
	}

}
