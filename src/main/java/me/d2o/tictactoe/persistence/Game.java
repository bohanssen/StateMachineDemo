package me.d2o.tictactoe.persistence;

import javax.persistence.Entity;

import me.d2o.statemachine.core.StateMachine;
import me.d2o.tictactoe.config.States;

@Entity
public class Game extends StateMachine{

	private char[][] board = new char[][]{{'.','.','.'},{'.','.','.'},{'.','.','.'}};

	@Override
	public String getInitialState() {
		return States.INITIAL;
	}

	public void printBoard(){
		System.out.println("  A B C");
		System.out.println(1+" "+board[0][0]+" "+board[0][1]+" "+board[0][2]);
		System.out.println(2+" "+board[1][0]+" "+board[1][1]+" "+board[1][2]);
		System.out.println(3+" "+board[2][0]+" "+board[2][1]+" "+board[2][2]);
	}
	
	public boolean pin(String c, String r, char m){
		int column = c.equalsIgnoreCase("A") ? 0 : c.equalsIgnoreCase("B") ? 1 : 2; 
		int row = Integer.parseInt(r)-1;
		if (board[row][column] == '.'){
			board[row][column] = m;
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkUtil(boolean check, char m, char x1, char x2, char x3){
		return (check || (m==x1 && m==x2 && m==x3));
	}
	
	public boolean checkVictory(char m){
		boolean victory = false;
		
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
}
