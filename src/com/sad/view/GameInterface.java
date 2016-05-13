package com.sad.view;

import com.sad.data.Move;

public class GameInterface
{
    private String defaultBoard = "o-----o-----o\n" + "| o---o---o |\n" + "| | o-o-o | |\n" + "o-o-o   o-o-o\n"
            + "| | o-o-o | |\n" + "| o---o---o |\n" + "o-----o-----o";

    private void displayError(Exception e)
    {

    }

    private Move getInput()
    {
        return new Move(new int[] {0, 0}, new int[] {0, 0});
    }
}
