package com.sad.framework;

public class GameParams
{
	private PlayerType player1;
	private PlayerType player2;

	public GameParams(PlayerType player1Type, PlayerType player2Type)
	{
		this.setPlayer1(player1Type);
		this.setPlayer2(player2Type);
	}

	/**
	 * @return the player1
	 */
	public PlayerType getPlayer1()
	{
		return player1;
	}

	/**
	 * @param player1
	 *            the player1 to set
	 */
	public void setPlayer1(PlayerType player1)
	{
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public PlayerType getPlayer2()
	{
		return player2;
	}

	/**
	 * @param player2
	 *            the player2 to set
	 */
	public void setPlayer2(PlayerType player2)
	{
		this.player2 = player2;
	}
}
