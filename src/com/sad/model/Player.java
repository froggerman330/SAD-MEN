package com.sad.model;

import com.sad.controller.GameController;
import com.sad.data.Move;

public abstract class Player
{
    protected GameController controller;

    public abstract Move movePiece();

    public abstract Move removePiece();
}
