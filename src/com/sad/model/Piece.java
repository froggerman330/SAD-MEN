package com.sad.model;

public class Piece
{
    private Player player;

    /**
     * Represents an empty board location.
     */
    public static Piece noPiece = new Piece(null);

    /**
     * Constructor for piece, sets the piece owner.
     * 
     * @param player
     *            the owner that owns the piece.
     */
    public Piece(Player player)
    {
        this.setPlayer(player);
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
