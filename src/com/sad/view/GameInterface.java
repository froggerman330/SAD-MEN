package com.sad.view;

import com.sad.data.Move;

public class GameInterface
{
    private static String defaultBoard = "1 2 3 4 5 6 7" + "1 o-----o-----o\n" + "2 | o---o---o |\n"
            + "3 | | o-o-o | |\n" + "4 o-o-o   o-o-o\n" + "5 | | o-o-o | |\n" + "6 | o---o---o |\n" + "7 o-----o-----o";

    public void displayError(Exception e)
    {

    }

    public Move getMove()
    {
        return new Move(new int[] {0, 0}, new int[] {0, 0}, null);
    }
}
