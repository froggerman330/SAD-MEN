package com.sad.model;

import com.sad.data.Move;

public class HumanPlayer extends Player
{
	@Override
	public Move getMove(Board board)
	{
		Move move = this.controller.getMoveFromUser(board);
		move.setPlayer(this);
		return move;
	}

}
