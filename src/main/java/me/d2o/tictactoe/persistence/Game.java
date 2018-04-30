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

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
}
