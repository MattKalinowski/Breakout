package com.games.breakout.gameobjects;

import com.games.breakout.Handler;

import java.awt.*;
import java.util.Iterator;

public class Block extends GameObject {

    public Block(float positionX, float positionY, Handler handler) {
        super(positionX, positionY, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) positionX, (int) positionY, 32, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) positionX, (int) positionY, 32, 16);
    }
}
