package com.sad.data;

import com.sad.model.Player;

public class Move
{
    private int[] previousPieceLocation = null;
    private int[] newPieceLocation = null;
    private Player player = null;

    public Move(int[] previousPieceLocation, int[] newPieceLocation, Player player)
    {
        this.previousPieceLocation = previousPieceLocation;
        this.newPieceLocation = newPieceLocation;
        this.setPlayer(player);
    }

    /**
     * @return the newPieceLocation
     */
    public int[] getNewPieceLocation()
    {
        return this.newPieceLocation;
    }

    /**
     * @param newPieceLocation
     *            the newPieceLocation to set
     */
    public void setNewPieceLocation(int[] newPieceLocation)
    {
        this.newPieceLocation = newPieceLocation;
    }

    /**
     * @return the previousPieceLocation
     */
    public int[] getPreviousPieceLocation()
    {
        return this.previousPieceLocation;
    }

    /**
     * @param previousPieceLocation
     *            the previousPieceLocation to set
     */
    public void setPreviousPieceLocation(int[] previousPieceLocation)
    {
        this.previousPieceLocation = previousPieceLocation;
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
