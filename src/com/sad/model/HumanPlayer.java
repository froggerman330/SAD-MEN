package com.sad.model;

import com.sad.data.Move;

public class HumanPlayer extends Player
{
    @Override
    public Move getMove(Board board)
    {
        return this.controller.getMoveFromUser(board);
    }

}
