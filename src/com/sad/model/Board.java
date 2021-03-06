package com.sad.model;

import java.util.HashMap;
import java.util.LinkedList;
import com.sad.controller.GameController;
import com.sad.data.Move;
import com.sad.framework.exceptions.IllegalMoveException;

/**
 * The board class. Keeps track of the board and does many functions that have
 * to do with the game model.
 * 
 * @author frogg
 *
 */
public class Board
{
	private Piece[][] board = { { Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece },
			{ null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null },
			{ null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null },
			{ Piece.noPiece, Piece.noPiece, Piece.noPiece, null, Piece.noPiece, Piece.noPiece, Piece.noPiece },
			{ null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null },
			{ null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null },
			{ Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece } };
	private String gameState = "placing"; // also moving and flying
	private LinkedList<Move> history = new LinkedList<Move>();
	private HashMap<Player, LinkedList<Piece>> pieces = new HashMap<Player, LinkedList<Piece>>();
	private HashMap<int[], int[][][]> millMap = new HashMap<int[], int[][][]>();
	private GameController controller;

	/**
	 * Constructor for the board. Takes each player and makes nine pieces for
	 * each player. Also initializes the millmap.
	 * 
	 * @param players
	 *            the piece owners.
	 */
	public Board(Player[] players)
	{
		// Make pieces
		for(Player player : players)
		{
			LinkedList<Piece> tempPieces = new LinkedList<Piece>();
			for(int i = 0; i < 9; i++)
			{
				tempPieces.add(new Piece(player));
			}
			this.pieces.put(player, tempPieces);
		}

		this.initializeMills();
	}

	/**
	 * Associates the other two locations for both vertical and horizontal mill
	 * formation for each playable intersection. Handles the knowing adjacent
	 * locations responsibility for the board class.
	 */
	private void initializeMills()
	{// knows adjacent intersections
		this.millMap.put(new int[] { 0, 0 }, new int[][][] { { { 0, 3 }, { 0, 6 } }, { { 3, 0 }, { 6, 0 } } });
		this.millMap.put(new int[] { 0, 3 }, new int[][][] { { { 0, 0 }, { 0, 6 } }, { { 1, 3 }, { 2, 3 } } });
		this.millMap.put(new int[] { 0, 6 }, new int[][][] { { { 0, 0 }, { 0, 3 } }, { { 3, 6 }, { 6, 6 } } });
		this.millMap.put(new int[] { 1, 1 }, new int[][][] { { { 1, 3 }, { 1, 5 } }, { { 3, 1 }, { 5, 1 } } });
		this.millMap.put(new int[] { 1, 3 }, new int[][][] { { { 1, 1 }, { 1, 5 } }, { { 0, 3 }, { 2, 3 } } });
		this.millMap.put(new int[] { 1, 5 }, new int[][][] { { { 1, 1 }, { 1, 3 } }, { { 3, 5 }, { 5, 5 } } });
		this.millMap.put(new int[] { 2, 2 }, new int[][][] { { { 2, 3 }, { 2, 4 } }, { { 3, 2 }, { 4, 2 } } });
		this.millMap.put(new int[] { 2, 3 }, new int[][][] { { { 2, 2 }, { 2, 4 } }, { { 0, 3 }, { 1, 3 } } });
		this.millMap.put(new int[] { 2, 4 }, new int[][][] { { { 2, 2 }, { 2, 3 } }, { { 3, 4 }, { 4, 4 } } });
		this.millMap.put(new int[] { 3, 0 }, new int[][][] { { { 3, 1 }, { 3, 2 } }, { { 0, 0 }, { 6, 0 } } });
		this.millMap.put(new int[] { 3, 1 }, new int[][][] { { { 3, 0 }, { 3, 2 } }, { { 1, 1 }, { 5, 1 } } });
		this.millMap.put(new int[] { 3, 2 }, new int[][][] { { { 3, 0 }, { 3, 1 } }, { { 2, 2 }, { 4, 2 } } });
		this.millMap.put(new int[] { 3, 4 }, new int[][][] { { { 3, 5 }, { 3, 6 } }, { { 2, 4 }, { 4, 4 } } });
		this.millMap.put(new int[] { 3, 5 }, new int[][][] { { { 3, 4 }, { 3, 6 } }, { { 1, 5 }, { 5, 5 } } });
		this.millMap.put(new int[] { 3, 6 }, new int[][][] { { { 3, 4 }, { 3, 5 } }, { { 0, 6 }, { 6, 6 } } });
		this.millMap.put(new int[] { 4, 2 }, new int[][][] { { { 4, 3 }, { 4, 4 } }, { { 2, 2 }, { 3, 2 } } });
		this.millMap.put(new int[] { 4, 3 }, new int[][][] { { { 4, 2 }, { 4, 4 } }, { { 5, 3 }, { 6, 3 } } });
		this.millMap.put(new int[] { 4, 4 }, new int[][][] { { { 4, 2 }, { 4, 3 } }, { { 2, 4 }, { 3, 4 } } });
		this.millMap.put(new int[] { 5, 1 }, new int[][][] { { { 5, 3 }, { 5, 5 } }, { { 1, 1 }, { 3, 1 } } });
		this.millMap.put(new int[] { 5, 3 }, new int[][][] { { { 5, 1 }, { 5, 5 } }, { { 4, 3 }, { 6, 3 } } });
		this.millMap.put(new int[] { 5, 5 }, new int[][][] { { { 5, 1 }, { 5, 3 } }, { { 1, 5 }, { 3, 5 } } });
		this.millMap.put(new int[] { 6, 0 }, new int[][][] { { { 6, 3 }, { 6, 6 } }, { { 0, 0 }, { 3, 0 } } });
		this.millMap.put(new int[] { 6, 3 }, new int[][][] { { { 6, 0 }, { 6, 6 } }, { { 4, 3 }, { 5, 3 } } });
		this.millMap.put(new int[] { 6, 6 }, new int[][][] { { { 6, 0 }, { 6, 3 } }, { { 0, 6 }, { 3, 6 } } });
	}

	/**
	 * Checks all the rules about moving. Split into three sections, removing a
	 * piece, placing a piece and moving a piece.
	 * 
	 * @param move
	 *            the move to make.
	 * @throws IllegalMoveException
	 *             if the move attempts to break any of the game rules.
	 */
	public void performMove(Move move) throws IllegalMoveException
	{
		if(move.getNewPieceLocation() == null && move.getPreviousPieceLocation() == null)
		{
			throw new IllegalMoveException("You must enter at least one location.");
		}
		else if(move.getNewPieceLocation() == null)
		{// removing a piece
			this.checkRemovePieceRules(move);

			this.removePieceAt(move.getPreviousPieceLocation());
		}
		else if(move.getPreviousPieceLocation() == null)
		{// adding a piece
			this.checkAddingPieceRules(move);

			this.setPieceAt(move.getNewPieceLocation(), this.getPieceFrom(move.getPlayer()));
		}
		else
		{// moving a piece
			this.checkMovingPieceRules(move);

			this.setPieceAt(move.getNewPieceLocation(), this.getPieceAt(move.getPreviousPieceLocation()));
			this.removePieceAt(move.getPreviousPieceLocation());
		}

		this.history.addFirst(move);

		if(this.isMillMove(move))
		{// resets the loop to get the remove piece move from the player.
			throw new IllegalMoveException("Please select the move to remove an opponents piece.");
		}
	}

	/**
	 * Checks the remove move to make sure that the player has formed a mill
	 * already by checking history.
	 * 
	 * @param move
	 *            the remove move.
	 * @throws IllegalMoveException
	 *             if the player has not formed a mill before trying to remove a
	 *             piece or the piece they are trying to remove is a mil and
	 *             there are other non-mill forming pieces on the board or if
	 *             the player tries to remove their own piece.
	 */
	private void checkRemovePieceRules(Move move) throws IllegalMoveException
	{
		if(!this.history.isEmpty())
		{
			if(this.history.peek().getPlayer() != this.getController().getCurrentPlayer())
			{
				throw new IllegalMoveException("You can only remove a piece if you have formed a mill.");
			}
		}

		int[] location = move.getPreviousPieceLocation();
		Player currentPlayer = move.getPlayer();
		Player nonCurrentPlayer = this.getController().getOtherPlayer(currentPlayer);
		Piece pieceAtLocation = this.board[location[0]][location[1]];

		if(pieceAtLocation == null || pieceAtLocation == Piece.noPiece)
		{
			throw new IllegalMoveException("There is no piece to remove at " + (location[0] + 1) + ", "
					+ (location[1] + 1) + ". Please select another piece.");
		}
		else if(this.isMill(nonCurrentPlayer, location) && this.isNonMillPieceOnBoard(nonCurrentPlayer))
		{
			throw new IllegalMoveException("There are other non-mill forming pieces on the board to remove.");
		}
		else if(pieceAtLocation.getPlayer() == currentPlayer)
		{
			throw new IllegalMoveException("You cannot remove your own piece.");
		}
	}

	/**
	 * Checks the adding a piece to the board rules.
	 * 
	 * @param move
	 *            the adding a piece move.
	 * @throws IllegalMoveException
	 *             if the player attempts to place a piece instead removing an
	 *             opponents one when forming a mill <b>or</b> if the player has
	 *             no pieces left to play.
	 */
	private void checkAddingPieceRules(Move move) throws IllegalMoveException
	{
		if(!this.history.isEmpty())
		{
			if(this.history.peek().getPlayer() == this.getController().getCurrentPlayer())
			{
				throw new IllegalMoveException("You may not place a piece instead of removing one for forming a mill.");
			}
		}

		if(this.countPiecesToPlay(this.getController().getCurrentPlayer()) == 0)
		{
			throw new IllegalMoveException("You have no more pieces left to play.");
		}

		Piece currentPiece = this.getPieceAt(move.getNewPieceLocation());
		if(currentPiece == Piece.noPiece)
		{
			return;
		}
		else if(currentPiece == null)
		{
			throw new IllegalMoveException("You cannot place a piece at " + (move.getNewPieceLocation()[0] + 1) + ", "
					+ (move.getNewPieceLocation()[1] + 1) + ".");
		}
		else
		{
			throw new IllegalMoveException("There is already a piece at " + (move.getNewPieceLocation()[0] + 1) + ", "
					+ (move.getNewPieceLocation()[1] + 1) + ".");
		}
	}

	/**
	 * Checks the rules around moving a piece from one location to another.
	 * 
	 * @param move
	 *            the move to move a piece from one place to another.
	 * @throws IllegalMoveException
	 *             if the game is not in the moving or flying stage <b>or</b>
	 *             the location that the piece is meant to be coming from does
	 *             not belong to the player trying to make the move or there's
	 *             no piece there <b>or</b> the new location is not an
	 *             intersection <b>or</b> there is already a piece at the new
	 *             location <b>or</b> if the game stage is flying, it does not
	 *             fit the flying rules.
	 */
	private void checkMovingPieceRules(Move move) throws IllegalMoveException
	{
		if(this.getGameState().equals("placing"))
		{
			throw new IllegalMoveException(
					"You cannot move a piece, the game is still in the " + this.getGameState() + " stage.");
		}

		Piece currentPiece = this.getPieceAt(move.getPreviousPieceLocation());
		if(currentPiece == null || currentPiece.getPlayer() != this.getController().getCurrentPlayer()
				|| currentPiece == Piece.noPiece)
		{
			throw new IllegalMoveException("You do not have a piece there to move.");
		}
		else if(this.getPieceAt(move.getNewPieceLocation()) == null)
		{
			throw new IllegalMoveException("You cannot move a piece to " + (move.getNewPieceLocation()[0] + 1) + ", "
					+ (move.getNewPieceLocation()[1] + 1) + ".");
		}
		else if(this.getPieceAt(move.getNewPieceLocation()) != Piece.noPiece)
		{
			throw new IllegalMoveException("There is already a piece at " + (move.getNewPieceLocation()[0] + 1) + ", "
					+ (move.getNewPieceLocation()[1] + 1) + ".");
		}

		if(!this.getGameState().toLowerCase().equals("flying"))
		{
			this.checkForIllegalFlyingMove(move);
		}
	}

	/**
	 * Checks to see if the move is illegal flying (one location to a
	 * non-adjacent one).
	 * 
	 * @param move
	 *            the move made.
	 * @throws IllegalMoveException
	 *             if the move is between non-adjacent locations.
	 */
	private void checkForIllegalFlyingMove(Move move) throws IllegalMoveException
	{
		int[] loc1 = move.getPreviousPieceLocation();
		int[] loc2 = move.getNewPieceLocation();
		if(loc2[0] != loc1[0] && loc2[1] != loc1[1])
		{// if the move is not either completely vertical or completely
			// horizontal
			throw new IllegalMoveException("You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1)
					+ ") to " + "(" + (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
		}

		if(loc2[1] == loc1[1])
		{// y locations the same
			switch(Math.abs(loc2[0] - loc1[0]))
			{// distance between x locations
				case 1:
					if(loc2[1] != 2 && loc2[1] != 3 && loc2[1] != 4)
					{// if it's not col 3, col 4 or col 5
						throw new IllegalMoveException(
								"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
										+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
					}
					break;
				case 2:
					if(loc2[1] != 1 && loc2[1] != 5)
					{// if it's not col 2 or col 6
						throw new IllegalMoveException(
								"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
										+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
					}
					break;
				case 3:
					if(loc2[1] != 0 && loc2[1] != 6)
					{// if it's not col 1 or col 7
						throw new IllegalMoveException(
								"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
										+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
					}
					break;
				default: // should be unreachable
					throw new IllegalMoveException(
							"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
									+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
			}
		}
		else
		{// x locations the same
			switch(Math.abs(loc2[1] - loc1[1]))
			{
				case 1:
					if(loc2[0] != 2 && loc2[0] != 3 && loc2[0] != 4)
					{// if it's not row 3, row 4 or row 5
						throw new IllegalMoveException(
								"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
										+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
					}
					break;
				case 2:
					if(loc2[0] != 1 && loc2[0] != 5)
					{// if it's not row 2 or row 6
						throw new IllegalMoveException(
								"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
										+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
					}
					break;
				case 3:
					if(loc2[0] != 0 && loc2[0] != 6)
					{// if it's not row 1 or row 7
						throw new IllegalMoveException(
								"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
										+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
					}
					break;
				default: // should be unreachable
					throw new IllegalMoveException(
							"You cannot move a piece from (" + (loc1[0] + 1) + ", " + (loc1[1] + 1) + ") to " + "("
									+ (loc2[0] + 1) + ", " + (loc2[1] + 1) + ") until you can fly.");
			}
		}
	}

	/**
	 * Checks to see if the <b>move</b> is creating a mill.
	 * 
	 * @param move
	 *            the move to check.
	 * @return <b>true</b> if the move forms a mill.
	 */
	private boolean isMillMove(Move move)
	{
		if(move.getPreviousPieceLocation() == null)
		{
			return this.isMill(move.getPlayer(), move.getNewPieceLocation());
		}
		else if(!this.getGameState().equals("placing") && move.getNewPieceLocation() != null)
		{
			return this.isMill(move.getPlayer(), move.getNewPieceLocation());
		}

		return false;
	}

	/**
	 * Removes a <i>piece</i> from the <i>board</i> at <b>location</b>
	 * 
	 * @param location
	 *            the location to remove the <i>piece</i> from the <i>board</i>
	 *            at.
	 * @throws IllegalMoveException
	 *             if the move being made is illegal.
	 */
	private void removePieceAt(int[] location) throws IllegalMoveException
	{
		this.board[location[0]][location[1]] = Piece.noPiece;
	}

	/**
	 * Checks the board to see if there are any pieces that do not form a mill
	 * for <b>player</b>.
	 * 
	 * @param player
	 *            the player to check for non-mill forming pieces on the board.
	 * @return <i>true</i> if there are non-mill forming pieces.
	 */
	private boolean isNonMillPieceOnBoard(Player player)
	{
		for(Piece[] pieceRow : this.board)
		{
			for(Piece p : pieceRow)
			{
				if(p != null && p != Piece.noPiece && p.getPlayer() == player)
				{
					if(!this.isMill(player, this.getLocationOfPiece(p)))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Finds the location of piece <b>p</b>.
	 * 
	 * @param p
	 *            the piece to find the location of.
	 * @return the location {i, j}.
	 */
	private int[] getLocationOfPiece(Piece p)
	{
		for(int i = 0; i < this.board.length; i++)
		{
			for(int j = 0; j < this.board[0].length; j++)
			{
				if(this.getPieceAt(new int[] { i, j }) == p)
				{
					return new int[] { i, j };
				}
			}
		}

		throw new RuntimeException("There is no piece that matches the input on the board.");
	}

	/**
	 * Counts the number of pieces a <b>player</b> has left.
	 * 
	 * @param player
	 *            the player to count the number of pieces of.
	 * @return the number of pieces <b>player</b> has left.
	 */
	public int countPiecesToPlay(Player player)
	{
		return this.pieces.get(player).size();
	}

	/**
	 * Counts the number of pieces on the board for <b>player</b>.
	 * 
	 * @param player
	 *            the player to count the pieces on the board of.
	 * @return the number of pieces on the board for <b>player</b>.
	 */
	public int countPiecesOnBoard(Player player)
	{
		int count = 0;
		for(Piece[] pieceRow : this.getBoard())
		{
			for(Piece p : pieceRow)
			{
				if(p != null && p != Piece.noPiece)
				{
					if(p.getPlayer() == player)
					{
						count++;
					}
				}
			}
		}

		return count;
	}

	/**
	 * Checks if a the piece at <b>location</b> is part of a mill for
	 * <b>player</b>.
	 * 
	 * @param player
	 *            the player to check if a location is a mill for.
	 * @param location
	 *            the location of the <i>piece</i>.
	 */
	private boolean isMill(Player player, int[] location)
	{
		for(int[] key : this.millMap.keySet())
		{
			boolean match = true;
			for(int i = 0; i < key.length; i++)
			{
				if(location[i] != key[i])
				{
					match = false;
				}
			}

			if(match)
			{
				for(int[][] mill : this.millMap.get(key))
				{
					boolean isMill = true;

					for(int[] intersection : mill)
					{
						Piece thisPiece = this.getPieceAt(intersection);
						if(thisPiece == Piece.noPiece || thisPiece.getPlayer() != player)
						{
							isMill &= false;
						}
					}

					if(isMill)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Checks to see if the game is over by counting the number of pieces on the
	 * board for each player.
	 * 
	 * @return <i>true</i> if the game is over.
	 */
	public boolean isGameOver()
	{
		for(int i = 1; i <= 2; i++)
		{
			Player p = this.getPlayer(i);
			if(this.countPiecesOnBoard(p) == 2 && this.countPiecesToPlay(p) == 0)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the gameState
	 */
	public String getGameState()
	{
		return this.gameState;
	}

	/**
	 * @param gameState
	 *            the gameState to set
	 */
	public void setGameState(String gameState)
	{
		this.gameState = gameState;
	}

	/**
	 * @return the board
	 */
	public Piece[][] getBoard()
	{
		return this.board;
	}

	/**
	 * Gets a piece from <b>player</b>.
	 * 
	 * @param player
	 *            the <i>player</i> to get the <i>piece</i> from.
	 * @return the piece from the player pieces.
	 */
	private Piece getPieceFrom(Player player)
	{
		return this.pieces.get(player).pop();
	}

	/**
	 * returns the piece at <b>location</b>.
	 * 
	 * @param location
	 *            the location on the board to return the piece at.
	 * @return a piece at <b>location</b>.
	 */
	private Piece getPieceAt(int[] location)
	{
		return this.board[location[0]][location[1]];
	}

	/**
	 * Sets a <b>piece</b> on the <i>board</i> at <b>location</b>.
	 * 
	 * @param location
	 *            the location on the board to set with <b>piece</b>.
	 * @param piece
	 *            the piece to set on the board in <b>location</b>.
	 */
	private void setPieceAt(int[] location, Piece piece)
	{
		this.board[location[0]][location[1]] = piece;

	}

	/**
	 * @return the controller
	 */
	public GameController getController()
	{
		return this.controller;
	}

	public void setGameController(GameController controller)
	{
		this.controller = controller;
	}

	/**
	 * @param playerNumber
	 *            the player number (for player 1 or player 2).
	 * @return the player with number <b>playerNumber</b>
	 */
	public Player getPlayer(int playerNumber)
	{
		return this.getController().getPlayer(playerNumber);
	}

	/**
	 * 
	 * @return the number of the current player.
	 */
	public int getCurrentPlayerNumber()
	{
		return this.getController().getCurrentPlayerNumber();
	}

	/**
	 * 
	 * @return the turn number.
	 */
	public int getTurnCount()
	{
		return this.getController().getTurnCount();
	}
}
