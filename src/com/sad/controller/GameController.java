package com.sad.controller;

import com.sad.model.Board;
import com.sad.model.Player;
import com.sad.view.GameInterface;

public class GameController
{
    private Player currentPlayer = null;
    private Board board;
    private Player[] players;
    private GameInterface view = new GameInterface();

    public GameController()
    {
        // get players from interface
        // set players
        // create board
    }

    private boolean isGameOver()
    {
        return true;
    }

    private void getMove()
    {

    }

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    /**
     * @param currentPlayer
     *            the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }
}
