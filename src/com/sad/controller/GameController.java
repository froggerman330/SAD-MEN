package com.sad.controller;

import com.sad.data.Move;
import com.sad.framework.exceptions.IllegalMoveException;
import com.sad.model.Board;
import com.sad.model.Player;
import com.sad.view.GameInterface;

public class GameController
{
    private Player currentPlayer = null;
    private Board board;
    private Player[] players;
    private GameInterface view;

    public GameController()
    {
        this.view = new GameInterface();
        // get players from interface
        // set players
        // create board
        this.startGameLoop();
    }

    private void startGameLoop()
    {
        while(!this.isGameOver())
        {
            // Change game state
            switch(this.board.getGameState())
            {
                case "placing":
                    if(this.board.countPiecesToPlay(this.getCurrentPlayer()) == 0)
                    {
                        this.board.setGameState("moving");
                    }
                    break;
                case "moving":
                    if(this.board.countPiecesOnBoard(this.getCurrentPlayer()) == 3)
                    {
                        this.board.setGameState("flying");
                    }
                    break;
            }

            try
            {
                // TODO: distinguish between play and remove but save state so that illegal remove prompts for another
                // remove.
                this.getMove();
                this.nextTurn();
            }
            catch(IllegalMoveException e)
            {
                this.view.displayError(e);
            }
        }
    }

    private void nextTurn()
    {
        for(Player p : this.players)
        {
            if(p != this.getCurrentPlayer())
            {
                this.setCurrentPlayer(p);
            }
        }
    }

    private boolean isGameOver()
    {
        return this.board.isGameOver();
    }

    private void getMove() throws IllegalMoveException
    {
        // TODO: change player based on setting or removing.
        Move move = this.view.getMove();
        move.setPlayer(this.getCurrentPlayer());
        this.board.performMove(move);
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
