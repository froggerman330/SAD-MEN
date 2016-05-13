package com.sad.view;

import com.sad.data.Move;

public class GameInterface
{
    private String defaultBoard = "o-----o-----o\n" + "| o---o---o |\n" + "| | o-o-o | |\n" + "o-o-o   o-o-o\n"
            + "| | o-o-o | |\n" + "| o---o---o |\n" + "o-----o-----o";

    public void displayError(Exception e)
    {

    }

    public Move getMove()
    {
        return new Move(new int[] {0, 0}, new int[] {0, 0});
    }
}
