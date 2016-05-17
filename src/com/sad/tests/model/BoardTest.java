package com.sad.tests.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.sad.controller.GameController;
import com.sad.model.Board;
import com.sad.model.Piece;

public class BoardTest
{
	GameController controller = new GameController();
	Board board = controller.getBoard();

	@Before
	public void setup()
	{
		this.board.setGameController(this.controller);
	}

	private void setNmuberOfPiecesOnBoard(int player1pieces, int player2pieces)
	{
		int piecesForPlayer1 = player1pieces;
		int piecesForPlayer2 = player2pieces;

		for(Piece[] pieceRow : this.board.getBoard())
		{
			for(Piece p : pieceRow)
			{
				if(p == Piece.noPiece)
				{
					if(piecesForPlayer1 > 0)
					{
						p.setPlayer(this.controller.getPlayer(1));
					}
					else if(piecesForPlayer2 > 0)
					{
						p.setPlayer(this.controller.getPlayer(2));
					}
				}
			}
		}
	}

	private void setSpecificPositions(int[][] player1Arrangement, int[][] player2Arrangement)
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				for(int[] loc : player1Arrangement)
				{
					if(loc[0] - 1 == i && loc[1] - 1 == j)
					{
						this.board.getBoard()[i][j] = new Piece(this.controller.getPlayer(1));
					}
				}

				for(int[] loc : player2Arrangement)
				{
					if(loc[0] - 1 == i && loc[1] - 1 == j)
					{
						this.board.getBoard()[i][j] = new Piece(this.controller.getPlayer(2));
					}
				}
			}
		}
	}

	@Test
	public void testCountPiecesOnEmptyBoard()
	{
		setNmuberOfPiecesOnBoard(0, 0);
		Assert.assertTrue(board.countPiecesOnBoard(this.controller.getPlayer(1)) == 0);
		Assert.assertTrue(board.countPiecesOnBoard(this.controller.getPlayer(2)) == 0);
	}

	@Test
	public void testCountPiecesOnBoardWithFourPiecesForPlayerOneAndThreeForPlayerTwo()
	{
		setNmuberOfPiecesOnBoard(4, 3);
		Assert.assertTrue(board.countPiecesOnBoard(this.controller.getPlayer(1)) == 4);
		Assert.assertTrue(board.countPiecesOnBoard(this.controller.getPlayer(2)) == 3);
	}

	@Test
	public void testIsGameOverFail()
	{
		setNmuberOfPiecesOnBoard(9, 9);
		Assert.assertFalse(board.isGameOver());
	}

	@Test
	public void testIsGameOverSpecificFail()
	{
		setSpecificPositions(
				new int[][] { { 1, 1 }, { 1, 4 }, { 1, 7 }, { 2, 2 }, { 2, 4 }, { 2, 6 }, { 4, 5 }, { 4, 6 } },
				new int[][]
		{ { 5, 4 }, { 6, 4 }, { 7, 4 } });
		board.setGameState("flying");
		Assert.assertFalse(board.isGameOver());
	}

}
