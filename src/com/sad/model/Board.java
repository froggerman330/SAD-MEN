package com.sad.model;

import java.util.HashMap;
import java.util.LinkedList;

import com.sad.data.Move;
import com.sad.framework.exceptions.IllegalMoveException;

public class Board
{
    private Piece[][] board = { {Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece},
            {null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null},
            {null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null},
            {Piece.noPiece, Piece.noPiece, Piece.noPiece, null, Piece.noPiece, Piece.noPiece, Piece.noPiece},
            {null, null, Piece.noPiece, Piece.noPiece, Piece.noPiece, null, null},
            {null, Piece.noPiece, null, Piece.noPiece, null, Piece.noPiece, null},
            {Piece.noPiece, null, null, Piece.noPiece, null, null, Piece.noPiece}};
    private String gameState = "placing"; // also moving and flying
    private LinkedList<Move> history = new LinkedList<Move>();
    private HashMap<Player, LinkedList<Piece>> pieces = new HashMap<Player, LinkedList<Piece>>();
    HashMap<Integer[], Integer[][][]> millMap = new HashMap<Integer[], Integer[][][]>();

    /**
     * Constructor for the board. Takes each player and makes nine pieces for each player. Also initializes the millmap.
     * 
     * @param players
     *            the piece owners.
     * @param controller
     *            the game controller.
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
     * Associates the other two locations for both vertical and horizontal mill formation for each playable
     * intersection. Handles the knowing adjacent locations responsibility for the board class.
     */
    private void initializeMills()
    {// knows adjacent intersections
        this.millMap.put(new Integer[] {0, 0}, new Integer[][][] { { {0, 3}, {0, 6}}, { {3, 0}, {6, 0}}});
        this.millMap.put(new Integer[] {0, 3}, new Integer[][][] { { {0, 0}, {0, 6}}, { {1, 3}, {2, 3}}});
        this.millMap.put(new Integer[] {0, 6}, new Integer[][][] { { {0, 0}, {0, 3}}, { {3, 6}, {6, 6}}});
        this.millMap.put(new Integer[] {1, 1}, new Integer[][][] { { {1, 3}, {1, 5}}, { {3, 1}, {5, 1}}});
        this.millMap.put(new Integer[] {1, 3}, new Integer[][][] { { {1, 1}, {1, 5}}, { {0, 3}, {2, 3}}});
        this.millMap.put(new Integer[] {1, 5}, new Integer[][][] { { {1, 1}, {1, 3}}, { {3, 5}, {5, 5}}});
        this.millMap.put(new Integer[] {2, 2}, new Integer[][][] { { {2, 3}, {2, 4}}, { {3, 2}, {4, 2}}});
        this.millMap.put(new Integer[] {2, 3}, new Integer[][][] { { {2, 2}, {2, 4}}, { {0, 3}, {1, 3}}});
        this.millMap.put(new Integer[] {2, 4}, new Integer[][][] { { {2, 2}, {2, 3}}, { {3, 4}, {4, 4}}});
        this.millMap.put(new Integer[] {3, 0}, new Integer[][][] { { {3, 1}, {3, 2}}, { {0, 0}, {6, 0}}});
        this.millMap.put(new Integer[] {3, 1}, new Integer[][][] { { {3, 0}, {3, 2}}, { {1, 1}, {5, 1}}});
        this.millMap.put(new Integer[] {3, 2}, new Integer[][][] { { {3, 0}, {3, 1}}, { {2, 2}, {4, 2}}});
        this.millMap.put(new Integer[] {3, 4}, new Integer[][][] { { {3, 5}, {3, 6}}, { {0, 6}, {6, 6}}});
        this.millMap.put(new Integer[] {3, 5}, new Integer[][][] { { {3, 4}, {3, 6}}, { {1, 5}, {5, 5}}});
        this.millMap.put(new Integer[] {3, 6}, new Integer[][][] { { {3, 4}, {3, 5}}, { {2, 4}, {4, 4}}});
        this.millMap.put(new Integer[] {4, 2}, new Integer[][][] { { {4, 3}, {4, 4}}, { {2, 2}, {3, 2}}});
        this.millMap.put(new Integer[] {4, 3}, new Integer[][][] { { {2, 2}, {2, 4}}, { {5, 3}, {6, 3}}});
        this.millMap.put(new Integer[] {4, 4}, new Integer[][][] { { {2, 2}, {2, 3}}, { {2, 4}, {3, 4}}});
        this.millMap.put(new Integer[] {5, 1}, new Integer[][][] { { {5, 3}, {5, 5}}, { {1, 1}, {3, 1}}});
        this.millMap.put(new Integer[] {5, 3}, new Integer[][][] { { {5, 1}, {5, 5}}, { {4, 3}, {6, 3}}});
        this.millMap.put(new Integer[] {5, 5}, new Integer[][][] { { {5, 1}, {5, 3}}, { {1, 5}, {3, 5}}});
        this.millMap.put(new Integer[] {6, 0}, new Integer[][][] { { {6, 3}, {6, 6}}, { {0, 0}, {3, 0}}});
        this.millMap.put(new Integer[] {6, 3}, new Integer[][][] { { {6, 0}, {6, 6}}, { {4, 3}, {5, 3}}});
        this.millMap.put(new Integer[] {6, 6}, new Integer[][][] { { {6, 0}, {6, 3}}, { {0, 6}, {3, 6}}});
    }

    public void performMove(Move move) throws IllegalMoveException
    {
        // TODO: make move and throw exception if illegal move
        if(move.getNewPieceLocation() == null)
        {// removing a piece from playerX
            this.removePieceAt(move.getPlayer(), move.getPreviousPieceLocation());
        }
        else if(move.getPreviousPieceLocation() == null)
        {// adding a piece for playerY
            this.setPieceAt(move.getNewPieceLocation(), this.getPieceFrom(move.getPlayer()));
        }
        else
        {// moving a piece

        }

        this.history.add(move);

        if(this.isMill(move.getPlayer(), move.getNewPieceLocation()))
        {
            this.doMillFormed();
        }
    }

    /**
     * Removes a <i>piece</i> from the <i>board</i> at <b>location</b>
     * 
     * @param player
     *            the player that a piece is being removed from.
     * @param location
     *            the location to remove the <i>piece</i> from the <i>board</i> at.
     * @throws IllegalMoveException
     *             if the move being made is illegal.
     */
    private void removePieceAt(Player player, int[] location) throws IllegalMoveException
    {
        Piece currentPiece = this.board[location[0]][location[1]];
        if(currentPiece == null || currentPiece == Piece.noPiece)
        {
            throw new IllegalMoveException("There is no piece to remove at " + location[0] + ", " + location[1] + ".");
        }
        else if(this.isMill(player, location) && this.isNonMillPieceOnBoard(player))
        {
            throw new IllegalMoveException("There are other non-mill forming pieces on the board to remove.");
        }

        this.board[location[0]][location[1]] = Piece.noPiece;
    }

    /**
     * Checks the board to see if there are any pieces that do not form a mill for <b>player</b>.
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
     * finds the location of piece <b>p</b>.
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
                if(this.getPieceAt(new int[] {i, j}) == p)
                {
                    return new int[] {i, j};
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
    {// knows how many pieces a player has left
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
     * Checks if a Mill has be formed by the most recent move.
     * 
     * @param player
     *            the player to check if a location is a mill for.
     * @param location
     *            the move that may have formed a mill.
     */
    private boolean isMill(Player player, int[] location)
    {
        for(Integer[][] mill : this.millMap.get(location))
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
    public boolean isGameOver()
    {
        if(this.gameState.equalsIgnoreCase("flying"))
        {
            int minCount = this.getMinPiecesLeft();

            if(minCount < 2)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Counts the number of pieces left for each player and returns the minimum.
     * 
     * @return the minimum number of pieces left for the players.
     */
    private int getMinPiecesLeft()
    {
        int countPlayer1 = 0;
        int countPlayer2 = 0;
        Player player1 = null, player2 = null;
        for(Piece[] pieceRow : this.getBoard())
        {
            for(Piece p : pieceRow)
            {
                if(p != null && p != Piece.noPiece)
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

        return countPlayer1 >= countPlayer2 ? countPlayer2 : countPlayer1;
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
        return this.board[location[0]][location[1]];
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
     * @throws IllegalMoveException
     *             if there is already a piece at the <b>location</b>.
     */
    public void setPieceAt(int[] location, Piece piece) throws IllegalMoveException
    {
        Piece currentPiece = this.board[location[0]][location[1]];
        if(currentPiece != Piece.noPiece)
        {
            throw new IllegalMoveException("There is already a piece at " + location[0] + ", " + location[1] + ".");
        }
        currentPiece = piece;
    }
}
