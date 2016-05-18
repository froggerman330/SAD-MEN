package com.sad.data;

import com.sad.model.Player;

/**
 * Describes the move of a player. Has a previous piece location which may be
 * null if the player is placing a new piece. Has a new piece location which may
 * be null if the player is removing an opponents piece. Knows the player that
 * made the move.
 * 
 * @author frogg
 *
 */
public class Move
{
	private int[] previousPieceLocation = null;
	private int[] newPieceLocation = null;
	private Player player = null;

	/**
	 * Constructor for move. Has previous piece location and new piece location.
	 * 
	 * @param previousPieceLocation
	 *            the previous location for the desired piece.
	 * @param newPieceLocation
	 *            the new location for the desired piece.
	 */
	public Move(int[] previousPieceLocation, int[] newPieceLocation)
	{
		this.previousPieceLocation = previousPieceLocation;
		this.newPieceLocation = newPieceLocation;
	}

	/**
	 * @return the newPieceLocation
	 */
	public int[] getNewPieceLocation()
	{
		return this.newPieceLocation;
	}

	/**
	 * @return the previousPieceLocation
	 */
	public int[] getPreviousPieceLocation()
	{
		return this.previousPieceLocation;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer()
	{
		return this.player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
