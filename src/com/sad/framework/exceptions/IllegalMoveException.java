package com.sad.framework.exceptions;

/**
 * IllegalMoveException, thrown when an illegal move has been attempted.
 * 
 * @author frogg
 *
 */
public class IllegalMoveException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            the message for why the exception has been thrown.
	 */
	public IllegalMoveException(String message)
	{
		super(message);
	}
}
