package com.sad.model;

import com.sad.data.Move;

/**
 * A human player. Calls on the view to get the move from a real human.
 * 
 * @author frogg
 *
 */
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
