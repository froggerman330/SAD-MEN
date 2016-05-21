package com.sad.controller;

import com.sad.data.Move;
import com.sad.framework.GameParams;
import com.sad.framework.PlayerFactory;
import com.sad.framework.exceptions.IllegalMoveException;
import com.sad.model.Board;
import com.sad.model.Player;
import com.sad.view.GameInterface;

/**
 * The controller from MVC class. It handles game flow, keeping track of current
 * player and the other player. Has a reference to the board and the view.
 * 
 * @author frogg
 *
 */
public class GameController
{
	private Player currentPlayer = null;
	private Board board;
	private Player[] players = new Player[2];
	private GameInterface view;
	private int turnCount = 1;

	/**
	 * Main program entrance.
	 * 
	 * @param args
	 *            unused.
	 */
	public static void main(String[] args)
	{
		GameController controller = new GameController();
		controller.setup();
		controller.startGameLoop();

	}

	/**
	 * The main game loop. Loops until game is over.
	 */
	private void startGameLoop()
	{
		this.setCurrentPlayer(this.players[0]);

		do
		{
			this.updateGameState();

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
		while(!this.board.isGameOver());

		this.view.displayMessage(
				"Game is over. Player " + this.getPlayerNumber(this.getOtherPlayer(getCurrentPlayer())) + " won.");
	}

	/**
	 * Sets up the view and gets the players from it before building the board.
	 */
	private void setup()
	{

		this.view = new GameInterface();

		GameParams gameParams = this.view.getGameParams();
		this.players[0] = PlayerFactory.buildPlayer(gameParams.getPlayer1());
		this.players[1] = PlayerFactory.buildPlayer(gameParams.getPlayer2());
		this.players[0].setGameController(this);
		this.players[1].setGameController(this);

		this.board = new Board(this.players);
		this.board.setGameController(this);
	}

	/**
	 * Updates the game state based on the number of pieces the current player
	 * has left.
	 */
	private void updateGameState()
	{
		if(this.board.countPiecesToPlay(currentPlayer) == 0
				&& this.board.countPiecesOnBoard(this.getCurrentPlayer()) == 3)
		{
			this.board.setGameState("flying");
		}
		else if(this.board.countPiecesToPlay(this.getCurrentPlayer()) == 0)
		{
			this.board.setGameState("moving");
		}
		else
		{
			this.board.setGameState("placing");
		}
	}

	/**
	 * Changes the current player to the other player and increments the turn
	 * count.
	 */
	private void nextTurn()
	{
		if(this.getCurrentPlayer() == this.getPlayer(2))
		{
			this.turnCount++;
		}
		this.setCurrentPlayer(this.getOtherPlayer(this.getCurrentPlayer()));
	}

	/**
	 * Gets the move from program user through view.
	 * 
	 * @param board
	 *            the current board.
	 * @return the move the user selected.
	 */
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

	/**
	 * Returns the <i>player</i> other than <b>player</b>.
	 * 
	 * @param player
	 *            the player that is not wanted.
	 * @return the other <i>player</i> that is not <b>player</b>.
	 */
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

	/**
	 * Gets the player associated with the player number (not index)
	 * 
	 * @param playerNumber
	 *            the player number (not index)
	 * @return the player with that player number (not index)
	 */
	public Player getPlayer(int playerNumber)
	{
		return this.players[playerNumber - 1];
	}

	/**
	 * 
	 * @return the current player number.
	 */
	public int getCurrentPlayerNumber()
	{
		return this.getCurrentPlayer() == this.players[0] ? 1 : 2;
	}

	/**
	 * Gets the player number associated with <b>player</b>.
	 * 
	 * @param player
	 *            the <i>player</i> to get the player number of.
	 * @return the player number of <b>player</b>.
	 */
	private int getPlayerNumber(Player player)
	{
		return players[0] == player ? 1 : 0;
	}

	/**
	 * @return the turnCount
	 */
	public int getTurnCount()
	{
		return turnCount;
	}
}
