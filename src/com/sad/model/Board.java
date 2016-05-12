package com.sad.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import com.sad.data.Move;

public class Board
{
    // knows where each of the pieces are on the board, also knows the layout of
    // the board.
    private Piece[][] board = { { Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece },
	    { null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null },
	    { null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null },
	    { Piece.noPiece, Piece.noPiece, Piece.noPiece, null, Piece.noPiece, Piece.noPiece, Piece.noPiece },
	    { null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null },
	    { null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null },
	    { Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece } };
    private String gameState = "placing";
    private LinkedList<Move> history = new LinkedList<Move>();
    private HashMap<Player, ArrayList<Piece>> pieces = new HashMap<Player, ArrayList<Piece>>();
    // List of mills
    HashMap<int[], int[][]> possibleMills = new HashMap<int[], int[][]>();

    /**
     * Constructor for the board. Takes each player and makes nine pieces for
     * each player.
     * 
     * @param players
     *            the piece owners.
     */
    public Board(Player[] players)
    {
	// Make pieces
	for(Player player : players)
	{
	    ArrayList<Piece> tempPieces = new ArrayList<Piece>();
	    for(int i = 0; i < 9; i++)
	    {
		tempPieces.add(new Piece(player));
	    }
	    this.pieces.put(player, tempPieces);
	}

	initializeMills();
    }

    private void initializeMills()
    {
	// possibleMills.put(new Integer({1,1}), 1);
    }

    private void performMove(Move move)
    {
	// TODO: make move
	history.add(move);
	millFormedCheck(move);
    }

    /**
     * Counts the number of pieces a player has left.
     * 
     * @param player
     *            the player to count the number of pieces of.
     * @return the number of pieces <i>player</i> has left.
     */
    private int countPieces(Player player)
    {
	return pieces.get(player).size();
    }

    /**
     * Checks if a Mill has be formed by the mose recent move.
     * 
     * @param move
     *            the move that may have formed a mill.
     */
    private void millFormedCheck(Move move)
    {
	int[] location = move.getNewPieceLocation();
	Player player = this.board[location[0]][location[1]].getPlayer();

	switch(location[0])
	{
	    case 1:
		switch(location[1])
		{
		    case 1:
			if(((this.board[1][1].getPlayer() == this.board[1][4].getPlayer())
				&& (this.board[1][1].getPlayer() == this.board[1][7].getPlayer()))
				|| ((this.board[1][1].getPlayer() == this.board[4][1].getPlayer())
					&& (this.board[1][1].getPlayer() == this.board[7][1].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 4:
			if(((this.board[1][4].getPlayer() == this.board[1][1].getPlayer())
				&& (this.board[1][4].getPlayer() == this.board[1][7].getPlayer()))
				|| ((this.board[1][4].getPlayer() == this.board[2][4].getPlayer())
					&& (this.board[1][4].getPlayer() == this.board[3][4].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 7:
			if(((this.board[1][7].getPlayer() == this.board[1][1].getPlayer())
				&& (this.board[1][7].getPlayer() == this.board[1][4].getPlayer()))
				|| ((this.board[1][7].getPlayer() == this.board[4][7].getPlayer())
					&& (this.board[1][7].getPlayer() == this.board[7][7].getPlayer())))
			{
			    millFormed();
			}
			break;
		}

		break;
	    case 2:
		switch(location[1])
		{
		    case 2:
			if(((this.board[2][2].getPlayer() == this.board[2][4].getPlayer())
				&& (this.board[2][2].getPlayer() == this.board[2][6].getPlayer()))
				|| ((this.board[2][2].getPlayer() == this.board[4][2].getPlayer())
					&& (this.board[2][2].getPlayer() == this.board[6][2].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 4:
			if(((this.board[2][4].getPlayer() == this.board[2][2].getPlayer())
				&& (this.board[2][4].getPlayer() == this.board[2][6].getPlayer()))
				|| ((this.board[2][4].getPlayer() == this.board[1][4].getPlayer())
					&& (this.board[2][4].getPlayer() == this.board[3][4].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 6:
			if(((this.board[2][6].getPlayer() == this.board[2][2].getPlayer())
				&& (this.board[2][6].getPlayer() == this.board[2][4].getPlayer()))
				|| ((this.board[2][6].getPlayer() == this.board[4][6].getPlayer())
					&& (this.board[2][6].getPlayer() == this.board[6][6].getPlayer())))
			{
			    millFormed();
			}
			break;
		}
	    case 3:
		switch(location[1])
		{
		    case 3:
			if(((this.board[3][3].getPlayer() == this.board[3][4].getPlayer())
				&& (this.board[3][3].getPlayer() == this.board[3][5].getPlayer()))
				|| ((this.board[3][3].getPlayer() == this.board[4][3].getPlayer())
					&& (this.board[3][3].getPlayer() == this.board[5][3].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 4:
			if(((this.board[3][4].getPlayer() == this.board[3][3].getPlayer())
				&& (this.board[3][4].getPlayer() == this.board[3][5].getPlayer()))
				|| ((this.board[3][4].getPlayer() == this.board[1][4].getPlayer())
					&& (this.board[3][4].getPlayer() == this.board[2][4].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 5:
			if(((this.board[3][5].getPlayer() == this.board[3][3].getPlayer())
				&& (this.board[3][5].getPlayer() == this.board[3][4].getPlayer()))
				|| ((this.board[3][5].getPlayer() == this.board[4][5].getPlayer())
					&& (this.board[3][5].getPlayer() == this.board[5][5].getPlayer())))
			{
			    millFormed();
			}
			break;
		}

		break;
	    case 4:
		switch(location[1])
		{
		    case 1:
			if(((this.board[4][1].getPlayer() == this.board[4][2].getPlayer())
				&& (this.board[4][1].getPlayer() == this.board[4][3].getPlayer()))
				|| ((this.board[4][1].getPlayer() == this.board[1][1].getPlayer())
					&& (this.board[4][1].getPlayer() == this.board[7][1].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 2:
			if(((this.board[4][2].getPlayer() == this.board[4][1].getPlayer())
				&& (this.board[4][2].getPlayer() == this.board[4][3].getPlayer()))
				|| ((this.board[4][1].getPlayer() == this.board[1][1].getPlayer())
					&& (this.board[4][1].getPlayer() == this.board[7][1].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 3:
			if(((this.board[4][3].getPlayer() == this.board[4][1].getPlayer())
				&& (this.board[4][3].getPlayer() == this.board[4][2].getPlayer()))
				|| ((this.board[4][3].getPlayer() == this.board[3][3].getPlayer())
					&& (this.board[4][3].getPlayer() == this.board[5][3].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 5:
			if(((this.board[4][5].getPlayer() == this.board[4][6].getPlayer())
				&& (this.board[4][5].getPlayer() == this.board[4][7].getPlayer()))
				|| ((this.board[4][5].getPlayer() == this.board[3][5].getPlayer())
					&& (this.board[4][5].getPlayer() == this.board[5][5].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 6:
			if(((this.board[4][6].getPlayer() == this.board[4][5].getPlayer())
				&& (this.board[4][6].getPlayer() == this.board[4][7].getPlayer()))
				|| ((this.board[4][6].getPlayer() == this.board[2][6].getPlayer())
					&& (this.board[4][6].getPlayer() == this.board[6][6].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 7:
			if(((this.board[4][7].getPlayer() == this.board[4][5].getPlayer())
				&& (this.board[4][7].getPlayer() == this.board[4][6].getPlayer()))
				|| ((this.board[4][7].getPlayer() == this.board[1][7].getPlayer())
					&& (this.board[4][7].getPlayer() == this.board[7][7].getPlayer())))
			{
			    millFormed();
			}
			break;
		}

		break;
	    case 5:
		switch(location[1])
		{
		    case 3:
			if(((this.board[5][3].getPlayer() == this.board[5][4].getPlayer())
				&& (this.board[5][3].getPlayer() == this.board[5][5].getPlayer()))
				|| ((this.board[5][3].getPlayer() == this.board[2][3].getPlayer())
					&& (this.board[5][3].getPlayer() == this.board[4][3].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 4:
			if(((this.board[5][4].getPlayer() == this.board[5][3].getPlayer())
				&& (this.board[5][4].getPlayer() == this.board[5][5].getPlayer()))
				|| ((this.board[5][4].getPlayer() == this.board[6][4].getPlayer())
					&& (this.board[5][4].getPlayer() == this.board[7][7].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 5:
			if(((this.board[5][5].getPlayer() == this.board[5][3].getPlayer())
				&& (this.board[5][5].getPlayer() == this.board[5][4].getPlayer()))
				|| ((this.board[5][5].getPlayer() == this.board[3][5].getPlayer())
					&& (this.board[5][5].getPlayer() == this.board[4][5].getPlayer())))
			{
			    millFormed();
			}
			break;
		}

		break;
	    case 6:
		switch(location[1])
		{
		    case 2:
			if(((this.board[6][2].getPlayer() == this.board[6][4].getPlayer())
				&& (this.board[6][2].getPlayer() == this.board[6][6].getPlayer()))
				|| ((this.board[6][2].getPlayer() == this.board[2][2].getPlayer())
					&& (this.board[6][2].getPlayer() == this.board[4][2].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 4:
			if(((this.board[6][4].getPlayer() == this.board[6][2].getPlayer())
				&& (this.board[6][4].getPlayer() == this.board[6][6].getPlayer()))
				|| ((this.board[6][4].getPlayer() == this.board[5][4].getPlayer())
					&& (this.board[6][4].getPlayer() == this.board[7][4].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 6:
			if(((this.board[6][6].getPlayer() == this.board[6][2].getPlayer())
				&& (this.board[6][6].getPlayer() == this.board[6][4].getPlayer()))
				|| ((this.board[6][6].getPlayer() == this.board[2][6].getPlayer())
					&& (this.board[6][6].getPlayer() == this.board[4][6].getPlayer())))
			{
			    millFormed();
			}
			break;
		}

		break;
	    case 7:
		switch(location[1])
		{
		    case 1:
			if(((this.board[7][1].getPlayer() == this.board[7][4].getPlayer())
				&& (this.board[7][1].getPlayer() == this.board[7][7].getPlayer()))
				|| ((this.board[7][1].getPlayer() == this.board[4][1].getPlayer())
					&& (this.board[7][1].getPlayer() == this.board[1][1].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 4:
			if(((this.board[7][4].getPlayer() == this.board[7][1].getPlayer())
				&& (this.board[7][4].getPlayer() == this.board[7][7].getPlayer()))
				|| ((this.board[7][4].getPlayer() == this.board[5][4].getPlayer())
					&& (this.board[7][4].getPlayer() == this.board[6][4].getPlayer())))
			{
			    millFormed();
			}
			break;
		    case 7:
			if(((this.board[7][7].getPlayer() == this.board[7][1].getPlayer())
				&& (this.board[7][7].getPlayer() == this.board[7][4].getPlayer()))
				|| ((this.board[7][7].getPlayer() == this.board[1][7].getPlayer())
					&& (this.board[7][7].getPlayer() == this.board[4][7].getPlayer())))
			{
			    millFormed();
			}
			break;
		}

		break;
	}
    }

    private void millFormed()
    {
	// TODO: pass to controller to get more input.

    }

    /**
     * Checks to see if the game is over by counting the number of pieces on the
     * board for each player.
     * 
     * @return <b>true</b> if the game is over.
     */
    private boolean isGameOver()
    {
	if(gameState.equalsIgnoreCase("flying"))
	{
	    int countPlayer1 = 0;
	    int countPlayer2 = 0;
	    Player player1 = null, player2 = null;
	    for(Piece[] pieceRow : this.board)
	    {
		for(Piece p : pieceRow)
		{
		    if(p != null)
		    {
			if(player1 == null)
			{
			    player1 = p.getPlayer();
			}
			else if(player2 == null)
			{
			    player2 = p.getPlayer();
			}

			if(player1 == p.getPlayer())
			{
			    countPlayer1++;
			}
			else if(player2 == p.getPlayer())
			{
			    countPlayer2++;
			}
		    }
		}
	    }

	    if(countPlayer1 < 2 || countPlayer2 < 2)
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
	return gameState;
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
	return board;
    }

    /**
     * @param board
     *            the board to set
     */
    public void setBoard(Piece[][] board)
    {
	this.board = board;
    }
}
