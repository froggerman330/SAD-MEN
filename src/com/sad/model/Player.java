package com.sad.model;

import com.sad.controller.GameController;
import com.sad.data.Move;

/**
 * A player of the game. Can be human, easy ai or hard ai.
 * 
 * @author frogg
 *
 */
public abstract class Player
{
	protected GameController controller;

	/**
	 * All players will be able to produce a move using the board information.
	 * 
	 * @param board
	 *            the board.
	 * @return a move to be made on the board.
	 */
	public abstract Move getMove(Board board);

	/**
	 * Sets the controller that is controlling the game.
	 * 
	 * @param gameController
	 *            the controller.
	 */
	public void setGameController(GameController gameController)
	{
		this.controller = gameController;
	}
}
