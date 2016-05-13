package com.sad.model;

import java.util.HashMap;
import java.util.LinkedList;

import com.sad.controller.GameController;
import com.sad.data.Move;

public class Board
{
    // knows where each of the pieces are on the board, also knows the layout of
    // the board.
    private Piece[][] board = { {Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece},
            {null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null},
            {null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null},
            {Piece.noPiece, Piece.noPiece, Piece.noPiece, null, Piece.noPiece, Piece.noPiece, Piece.noPiece},
            {null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null},
            {null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null},
            {Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece}};
    private String gameState = "placing";
    private LinkedList<Move> history = new LinkedList<Move>();
    private HashMap<Player, LinkedList<Piece>> pieces = new HashMap<Player, LinkedList<Piece>>();
    // List of mills
    HashMap<Integer[], Integer[][][]> possibleMills = new HashMap<Integer[], Integer[][][]>();
    private GameController controller = null;

    /**
     * Constructor for the board. Takes each player and makes nine pieces for each player.
     * 
     * @param players
     *            the piece owners.
     */
    public Board(Player[] players, GameController controller)
    {
        this.controller = controller;
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
     * Associates the other two locations for both vertical and horizontal mill formation for each playable
     * intersection. Handles the knowing adjacent locations responsibility for the board class.
     */
    private void initializeMills()
    {// knows adjacent intersections
        this.possibleMills.put(new Integer[] {1, 1}, new Integer[][][] { { {1, 4}, {1, 7}}, { {4, 1}, {7, 1}}});
        this.possibleMills.put(new Integer[] {1, 4}, new Integer[][][] { { {1, 1}, {1, 7}}, { {2, 4}, {3, 4}}});
        this.possibleMills.put(new Integer[] {1, 7}, new Integer[][][] { { {1, 1}, {1, 4}}, { {4, 7}, {7, 7}}});
        this.possibleMills.put(new Integer[] {2, 2}, new Integer[][][] { { {2, 4}, {2, 6}}, { {4, 2}, {6, 2}}});
        this.possibleMills.put(new Integer[] {2, 4}, new Integer[][][] { { {2, 2}, {2, 6}}, { {1, 4}, {3, 4}}});
        this.possibleMills.put(new Integer[] {2, 6}, new Integer[][][] { { {2, 2}, {2, 4}}, { {4, 6}, {6, 6}}});
        this.possibleMills.put(new Integer[] {3, 3}, new Integer[][][] { { {3, 4}, {3, 5}}, { {4, 3}, {5, 3}}});
        this.possibleMills.put(new Integer[] {3, 4}, new Integer[][][] { { {3, 3}, {3, 5}}, { {1, 4}, {2, 4}}});
        this.possibleMills.put(new Integer[] {3, 5}, new Integer[][][] { { {3, 3}, {3, 4}}, { {4, 5}, {5, 5}}});
        this.possibleMills.put(new Integer[] {4, 1}, new Integer[][][] { { {4, 2}, {4, 3}}, { {1, 1}, {7, 1}}});
        this.possibleMills.put(new Integer[] {4, 2}, new Integer[][][] { { {4, 1}, {4, 3}}, { {2, 2}, {6, 2}}});
        this.possibleMills.put(new Integer[] {4, 3}, new Integer[][][] { { {4, 1}, {4, 2}}, { {3, 3}, {5, 3}}});
        this.possibleMills.put(new Integer[] {4, 5}, new Integer[][][] { { {4, 6}, {4, 7}}, { {1, 7}, {7, 7}}});
        this.possibleMills.put(new Integer[] {4, 6}, new Integer[][][] { { {4, 5}, {4, 7}}, { {2, 6}, {6, 6}}});
        this.possibleMills.put(new Integer[] {4, 7}, new Integer[][][] { { {4, 5}, {4, 6}}, { {3, 5}, {5, 5}}});
        this.possibleMills.put(new Integer[] {5, 3}, new Integer[][][] { { {5, 4}, {5, 5}}, { {3, 3}, {4, 3}}});
        this.possibleMills.put(new Integer[] {5, 4}, new Integer[][][] { { {3, 3}, {3, 5}}, { {6, 4}, {7, 4}}});
        this.possibleMills.put(new Integer[] {5, 5}, new Integer[][][] { { {3, 3}, {3, 4}}, { {3, 5}, {4, 5}}});
        this.possibleMills.put(new Integer[] {6, 2}, new Integer[][][] { { {6, 4}, {6, 6}}, { {2, 2}, {4, 2}}});
        this.possibleMills.put(new Integer[] {6, 4}, new Integer[][][] { { {6, 2}, {6, 6}}, { {5, 4}, {7, 4}}});
        this.possibleMills.put(new Integer[] {6, 6}, new Integer[][][] { { {6, 2}, {6, 4}}, { {2, 6}, {4, 6}}});
        this.possibleMills.put(new Integer[] {7, 1}, new Integer[][][] { { {7, 4}, {7, 7}}, { {1, 1}, {4, 1}}});
        this.possibleMills.put(new Integer[] {7, 4}, new Integer[][][] { { {7, 1}, {7, 7}}, { {5, 4}, {6, 4}}});
        this.possibleMills.put(new Integer[] {7, 7}, new Integer[][][] { { {7, 1}, {7, 4}}, { {1, 7}, {4, 7}}});
    }

    private void performMove(Move move)
    {
        // TODO: make move and throw exception if illegal move
        if(move.getNewPieceLocation() == null)
        {// removing a piece
            this.removePieceAt(move.getPreviousPieceLocation());
        }
        else if(move.getPreviousPieceLocation() == null)
        {// adding a piece
            this.setPieceAt(move.getNewPieceLocation(), this.getPieceFrom(this.controller.getCurrentPlayer()));
        }

        this.history.add(move);

        if(this.isMillFormed(move))
        {
            this.doMillFormed();
        }
    }

    /**
     * Removes a <i>piece</i> from the <i>board</i> at <b>location</b>
     * 
     * @param location
     *            the location to remove the <i>piece</i> from the <i>board</i> at.
     */
    private void removePieceAt(int[] location)
    {
        this.board[location[0]][location[1]] = Piece.noPiece;
    }

    /**
     * Counts the number of pieces a player has left.
     * 
     * @param player
     *            the player to count the number of pieces of.
     * @return the number of pieces <b>player</b> has left.
     */
    private int countPieces(Player player)
    {// knows how many pieces a player has left
        return this.pieces.get(player).size();
    }

    /**
     * Checks if a Mill has be formed by the most recent move.
     * 
     * @param move
     *            the move that may have formed a mill.
     */
    private boolean isMillFormed(Move move)
    {
        int[] location = move.getNewPieceLocation();
        Player player = this.board[location[0]][location[1]].getPlayer();

        for(Integer[][] mill : this.possibleMills.get(location))
        {
            boolean millFormed = true;

            for(Integer[] intersection : mill)
            {
                if(this.getPieceAt(intersection) != Piece.noPiece)
                {
                    if(this.getPieceAt(intersection).getPlayer() != player)
                    {
                        millFormed = millFormed & false;
                    }
                }
            }

            if(millFormed)
            {
                return true;
            }
        }

        return false;
    }

    private void doMillFormed()
    {
        // TODO: pass to controller to get more input.

    }

    /**
     * Checks to see if the game is over by counting the number of pieces on the board for each player.
     * 
     * @return <i>true</i> if the game is over.
     */
    private boolean isGameOver()
    {
        if(this.gameState.equalsIgnoreCase("flying"))
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
        if(this.board[location[0]][location[1]] != null)
        {
            return this.board[location[0]][location[1]];
        }

        return Piece.noPiece;
    }

    /**
     * returns the piece at <b>location</b>.
     * 
     * @param location
     *            the location on the board to return the piece at.
     * @return a piece at <b>location</b>.
     */
    private Piece getPieceAt(Integer[] location)
    {
        int[] intersection = {location[0], location[1]};
        return this.getPieceAt(intersection);
    }

    /**
     * Sets a <b>piece</b> on the <i>board</i> at <b>location</b>.
     * 
     * @param location
     *            the location on the board to set with <b>piece</b>.
     * @param piece
     *            the piece to set on the board in <b>location</b>.
     */
    public void setPieceAt(int[] location, Piece piece)
    {
        this.board[location[0]][location[1]] = piece;
    }
}
