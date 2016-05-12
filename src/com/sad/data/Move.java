package com.sad.data;

public class Move
{
    private int[] previousPieceLocation = null;
    private int[] newPieceLocation = null;

    /**
     * @return the newPieceLocation
     */
    public int[] getNewPieceLocation()
    {
	return newPieceLocation;
    }

    /**
     * @param newPieceLocation
     *            the newPieceLocation to set
     */
    public void setNewPieceLocation(int[] newPieceLocation)
    {
	this.newPieceLocation = newPieceLocation;
    }

    /**
     * @return the previousPieceLocation
     */
    public int[] getPreviousPieceLocation()
    {
	return previousPieceLocation;
    }

    /**
     * @param previousPieceLocation
     *            the previousPieceLocation to set
     */
    public void setPreviousPieceLocation(int[] previousPieceLocation)
    {
	this.previousPieceLocation = previousPieceLocation;
    }
}
