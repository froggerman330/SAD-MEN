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

/**
 * The game interface or view. Displays things on the console and gets human
 * input.
 * 
 * @author frogg
 *
 */
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

	/**
	 * On creation, prints the board for everybody to see.
	 */
	public GameInterface()
	{
		this.printBoard();
	}

	/**
	 * Prints the board character for character.
	 */
	private void printBoard()
	{
		System.out.println("\n" + horizontalCols);
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

	/**
	 * Display an error message to the user.
	 * 
	 * @param e
	 *            the error to get the message from.
	 */
	public void displayError(Exception e)
	{
		this.displayMessage(e.getMessage());
	}

	/**
	 * Display any message to the user.
	 * 
	 * @param msg
	 *            the message to display.
	 */
	public void displayMessage(String msg)
	{
		System.out.println("\n !MESSAGE FROM SYSTEM:");
		System.out.println(msg);
	}

	/**
	 * Prompt the human user for a move.
	 * 
	 * @param board
	 *            the board that the user needs to see to produce the move.
	 * @return the human user's move.
	 */
	public Move getMoveFromUser(Board board)
	{
		this.updateView(board);

		System.out.println("Turn " + board.getTurnCount() + " for player number " + board.getCurrentPlayerNumber()
				+ ", game state is " + board.getGameState() + ".\n");

		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter piece starting location row,col (or blank for playing a new piece)");
			String startingLocation = in.readLine().toLowerCase();
			System.out.println("You entered: " + startingLocation);
			System.out.println("Please enter desired piece location row,col (or blank for removing a piece)");
			String desiredLocation = in.readLine().toLowerCase();
			System.out.println("You entered: " + desiredLocation);
			int[][] locations = this.parseMove(startingLocation, desiredLocation);
			return new Move(locations[0], locations[1]);
		}
		catch(IOException e)
		{
			this.displayError(e);
		}

		return new Move(new int[] { 4, 4 }, new int[] { 4, 4 });
		// dummy values of board center to trip illegal move, unreachable in
		// normal operation.
	}

	/**
	 * Updates the internal state of the board to match the current <b>board</b>
	 * .
	 * 
	 * @param board
	 *            the board to get the state from.
	 */
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

	/**
	 * Parses the strings given by user input and turns them into {{x,y},{x,y}}.
	 * 
	 * @param startingLocation
	 *            the starting location string.
	 * @param desiredLocation
	 *            the desired location string.
	 * @return a two dimensional array containing two coordinate pairs, starting
	 *         and desired locations.w
	 */
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
