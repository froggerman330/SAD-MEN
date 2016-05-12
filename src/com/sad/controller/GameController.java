package com.sad.controller;

import com.sad.model.Board;
import com.sad.model.Player;
import com.sad.view.GameInterface;

public class GameController
{
    private Player currentTurn = null;
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

}
