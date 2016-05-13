package com.sad.controller;

import com.sad.framework.exceptions.IllegalMoveException;
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
        this.startGameLoop();
    }

    private void startGameLoop()
    {
        while(!this.isGameOver())
        {
            switch(this.board.getGameState())
            {
                case "placing":
                    if(this.board.countPieces(this.getCurrentPlayer()) == 0)
                    {
                        this.board.setGameState("moving");
                    }
                case "moving":
                case "flying":
            }
        }
    }

    private boolean isGameOver()
    {
        return this.board.isGameOver();
    }

    private void getMove() throws IllegalMoveException
    {
        this.board.performMove(this.view.getMove());
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
