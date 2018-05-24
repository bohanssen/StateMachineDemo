package me.d2o.tictactoe.persistence;

import javax.persistence.Entity;

import me.d2o.tictactoe.config.States;
import me.d2o.statemachine.core.StateMachine;

@Entity
public class Game extends StateMachine{

	private char[][] board = new char[][]{{'.','.','.'},{'.','.','.'},{'.','.','.'}};

	private int turn;
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	@Override
	public String getInitialState() {
		return States.INITIAL;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
}
