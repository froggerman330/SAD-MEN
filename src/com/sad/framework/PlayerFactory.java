package com.sad.framework;

import com.sad.model.EasyAIPlayer;
import com.sad.model.HardAIPlayer;
import com.sad.model.HumanPlayer;
import com.sad.model.Player;

/**
 * The player factory, can create three types of players. Human players are
 * played by the game users. EasyAI and HardAI are for AI players but are not
 * implemented in this situation.
 * 
 * @author frogg
 *
 */
public class PlayerFactory
{
	/**
	 * The factory for producing <i>players</i>.
	 * 
	 * @param playerType
	 *            the type of <i>player</i> to produce.
	 * @return the new <i>player</i>.
	 */
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
				throw new RuntimeException(
						"Trying to create a player type that doesn't exist. PlayerType: " + playerType);
		}
	}
}
