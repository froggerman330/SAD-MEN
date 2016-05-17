package com.sad.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.sad.data.Move;
import com.sad.framework.GameParams;
import com.sad.framework.PlayerType;
import com.sad.model.Board;
import com.sad.model.Piece;

public class GameInterface
{
	private static String horizontalCols = "  1 2 3 4 5 6 7";
	private static String[] verticalRows = { "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 " };
	private char[][] boardView = { { 'o', '-', '-', '-', '-', '-', 'o', '-', '-', '-', '-', '-', 'o' },
			{ '|', ' ', 'o', '-', '-', '-', 'o', '-', '-', '-', 'o', ' ', '|' },
			{ '|', ' ', '|', ' ', 'o', '-', 'o', '-', 'o', ' ', '|', ' ', '|' },
			{ 'o', '-', 'o', '-', 'o', ' ', ' ', ' ', 'o', '-', 'o', '-', 'o' },
			{ '|', ' ', '|', ' ', 'o', '-', 'o', '-', 'o', ' ', '|', ' ', '|' },
			{ '|', ' ', 'o', '-', '-', '-', 'o', '-', '-', '-', 'o', ' ', '|' },
			{ 'o', '-', '-', '-', '-', '-', 'o', '-', '-', '-', '-', '-', 'o' } };

	public GameInterface()
	{
		this.printBoard();
	}

	private void printBoard()
	{
		System.out.println(horizontalCols);
		for(int i = 0; i < this.boardView.length; i++)
		{// each row
			System.out.print(verticalRows[i]);
			for(int j = 0; j < this.boardView[0].length; j++)
			{// each cell
				System.out.print(this.boardView[i][j]);
			}
			System.out.println("");
		}

		System.out.println("");
	}

	public void displayError(Exception e)
	{
		// switch(e)
		System.out.println(e.getMessage());
	}

	public Move getMoveFromUser(Board board)
	{
		this.updateView(board);

		System.out.println("Turn " + board.getController().getTurnCount() + " for player number "
				+ board.getCurrentPlayerNumber() + ", game state is " + board.getGameState() + ".\n");

		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter piece starting location x,y (or blank for playing a new piece)");
			String startingLocation = in.readLine().toLowerCase();
			System.out.println("Please enter desired piece location x,y (or blank for removing a piece)");
			String desiredLocation = in.readLine().toLowerCase();
			int[][] locations = this.parseMove(startingLocation, desiredLocation);
			return new Move(locations[0], locations[1], board.getCurrentPlayer());
		}
		catch(IOException e)
		{
			this.displayError(e);
		}

		return new Move(new int[] { 4, 4 }, new int[] { 4, 4 }, null);
		// dummy values of board center to trip illegal move.
	}

	private void updateView(Board board)
	{// update with new board
		for(int i = 0; i < board.getBoard().length; i++)
		{// each row
			for(int j = 0; j < board.getBoard()[0].length; j++)
			{// each cell
				if(board.getBoard()[i][j] == null)
				{
					// this.boardView[i][j*2] = '-'; // but should never change
				}
				else if(board.getBoard()[i][j] == Piece.noPiece)
				{
					this.boardView[i][j * 2] = 'o';
				}
				else if(board.getBoard()[i][j].getPlayer() == board.getPlayer(1))
				{
					this.boardView[i][j * 2] = '1';
				}
				else
				{
					this.boardView[i][j * 2] = '2';
				}
			}
		}

		this.printBoard();
	}

	/**
	 * Gets the game parameters from the human in front of the display.
	 * 
	 * @return the gameParams (each player type).
	 */
	public GameParams getGameParams()
	{
		boolean playersSet = false;
		int playerCount = 1;
		PlayerType players[] = new PlayerType[2];

		while(!playersSet)
		{
			System.out.println("Please type in the player type for player " + playerCount
					+ " (Player 1 will play first) [Available types = HUMAN, EASYAI, HARDAI].");
			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String playerType = in.readLine().toLowerCase();
				switch(playerType)
				{
					case "human":
						players[playerCount - 1] = PlayerType.HUMAN;
						playerCount++;
						break;
					case "easyai":
						System.out.println("AI are not implemented for this assignment. Sorry.");
						// players[playerCount - 1] = PlayerType.EASY_AI;
						// playerCount++;
						break;
					case "hardai":
						System.out.println("AI are not implemented for this assignment. Sorry.");
						// players[playerCount - 1] = PlayerType.HARD_AI;
						// playerCount++;
						break;
					default:
						System.out.println(playerType.toUpperCase() + " is not a valid player type.");
				}
			}
			catch(IOException e)
			{
				this.displayError(e);
			}

			if(playerCount == 3)
			{
				playersSet = true;
			}
		}

		return new GameParams(players[0], players[1]);
	}

	private int[][] parseMove(String startingLocation, String desiredLocation)
	{
		int[] startingLocationArray = null;
		int[] desiredLocationArray = null;
		Pattern pattern = Pattern.compile("[1-7],[1-7]");
		Matcher startingMatch = pattern.matcher(startingLocation);
		Matcher desiredMatch = pattern.matcher(desiredLocation);

		if(startingMatch.matches())
		{
			startingLocationArray = new int[] { Integer.parseInt(startingLocation.substring(0, 1)) - 1,
					Integer.parseInt(startingLocation.substring(2, 3)) - 1 };
		}

		if(desiredMatch.matches())
		{
			desiredLocationArray = new int[] { Integer.parseInt(desiredLocation.substring(0, 1)) - 1,
					Integer.parseInt(desiredLocation.substring(2, 3)) - 1 };
		}

		return new int[][] { startingLocationArray == null ? null : startingLocationArray,
				desiredLocationArray == null ? null : desiredLocationArray };

	}
}
