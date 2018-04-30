package me.d2o.tictactoe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.d2o.tictactoe.persistence.Game;
import me.d2o.tictactoe.persistence.GameRepository;

@Service
@Transactional
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	public Game initializeNew(){
		Game game = new Game();
		gameRepository.save(game);
		return game;
	}
	
	public Game get(String id){
		return gameRepository.findById(id).get();
	}
	
	public void printBoard(String id){
		char[][] board = get(id).getBoard();
		System.out.println("  A B C");
		System.out.println(1+" "+board[0][0]+" "+board[0][1]+" "+board[0][2]);
		System.out.println(2+" "+board[1][0]+" "+board[1][1]+" "+board[1][2]);
		System.out.println(3+" "+board[2][0]+" "+board[2][1]+" "+board[2][2]);
	}
	
	public boolean setPin(String id,String c, String r, char m){
		char[][] board = get(id).getBoard();
		int column = c.equalsIgnoreCase("A") ? 0 : c.equalsIgnoreCase("B") ? 1 : 2; 
		int row = Integer.parseInt(r)-1;
		if (board[row][column] == '.'){
			board[row][column] = m;
			get(id).setBoard(board);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkVictory(String id, char m){
		boolean victory = false;
		char[][] board = get(id).getBoard();
		victory = checkUtil(victory, m, board[0][0], board[0][1], board[0][2]);
		victory = checkUtil(victory, m, board[1][0], board[1][1], board[1][2]);
		victory = checkUtil(victory, m, board[2][0], board[2][1], board[2][2]);
		
		victory = checkUtil(victory, m, board[0][0], board[1][0], board[2][0]);
		victory = checkUtil(victory, m, board[0][1], board[1][1], board[2][1]);
		victory = checkUtil(victory, m, board[0][2], board[1][2], board[2][2]);
		
		victory = checkUtil(victory, m, board[0][0], board[1][1], board[2][2]);
		victory = checkUtil(victory, m, board[0][2], board[1][1], board[2][0]);
		return victory;
	}
	
	private boolean checkUtil(boolean check, char m, char x1, char x2, char x3){
		return (check || (m==x1 && m==x2 && m==x3));
	}
}
