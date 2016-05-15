package com.sad.framework;

import com.sad.model.EasyAIPlayer;
import com.sad.model.HardAIPlayer;
import com.sad.model.HumanPlayer;
import com.sad.model.Player;

public class PlayerFactory
{
    private static PlayerFactory instance = null;

    public static Player buildPlayer(PlayerType playerType)
    {
        switch(playerType)
        {
            case HUMAN:
                return new HumanPlayer();
            case EASY_AI:
                return new EasyAIPlayer();
            case HARD_AI:
                return new HardAIPlayer();
            default:
                throw new RuntimeException("Trying to create a player type that doesn't exist. PlayerType: "
                        + playerType);
        }
    }

    public static PlayerFactory getInstance()
    {
        if(instance == null)
        {
            instance = new PlayerFactory();
        }

        return instance;
    }
}
