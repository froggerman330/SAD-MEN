package com.sad.framework;

/**
 * The two player types for the game as a class.
 * 
 * @author frogg
 *
 */
public class GameParams
{
	private PlayerType player1;
	private PlayerType player2;

	/**
	 * 
	 * @param player1Type
	 *            the PlayerType for player 1.
	 * @param player2Type
	 *            the PlayerType for player 2.
	 */
	public GameParams(PlayerType player1Type, PlayerType player2Type)
	{
		this.player1 = player1Type;
		this.player2 = player2Type;
	}

	/**
	 * @return the player1
	 */
	public PlayerType getPlayer1()
	{
		return this.player1;
	}

	/**
	 * @return the player2
	 */
	public PlayerType getPlayer2()
	{
		return this.player2;
	}
}
