package com.sad.controller;

import com.sad.data.Move;
import com.sad.framework.GameParams;
import com.sad.framework.PlayerFactory;
import com.sad.framework.exceptions.IllegalMoveException;
import com.sad.model.Board;
import com.sad.model.Player;
import com.sad.view.GameInterface;

public class GameController
{
    private Player currentPlayer = null;
    private Board board;
    private Player[] players = new Player[2];
    private GameInterface view;

    public GameController()
    {
        this.view = new GameInterface();
        GameParams gameParams = this.view.getGameParams();
        this.players[0] = PlayerFactory.buildPlayer(gameParams.getPlayer1());
        this.players[1] = PlayerFactory.buildPlayer(gameParams.getPlayer2());
        this.board = new Board(this.players);
        this.setCurrentPlayer(this.players[0]);
        this.startGameLoop();
    }

    public static void main(String[] args)
    {
        new GameController();
    }

    private void startGameLoop()
    {
        this.players[0].setGameController(this);
        this.players[1].setGameController(this);
        this.board.setGameController(this);

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
                this.board.performMove(this.getCurrentPlayer().getMove(this.board));

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
        this.setCurrentPlayer(this.getOtherPlayer(this.getCurrentPlayer()));
    }

    private boolean isGameOver()
    {
        return this.board.isGameOver();
    }

    public Move getMoveFromUser(Board board)
    {
        return this.view.getMoveFromUser(board);
    }

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public Player getOtherPlayer(Player player)
    {
        return player == this.players[0] ? this.players[1] : this.players[0];
    }

    /**
     * @param currentPlayer
     *            the currentPlayer to set
     */
    private void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public Player getPlayer(int playerNumber)
    {
        return this.players[playerNumber - 1];
    }

    public int getCurrentPlayerNumber()
    {
        return this.getCurrentPlayer() == this.players[0] ? 1 : 2;
    }
}
