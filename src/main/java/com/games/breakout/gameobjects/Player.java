package com.games.breakout.gameobjects;

import com.games.breakout.Game;
import com.games.breakout.Handler;
import com.games.breakout.services.PositionHelper;

import java.awt.*;

public class Player extends GameObject {

    public Player(float positionX, float positionY, Handler handler) {
        super(positionX, positionY, handler);
    }

    @Override
    public void tick() {
        positionX += velocityX;
        positionX = PositionHelper.clamp(positionX, 0, Game.WIDTH - 96);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) positionX, (int) positionY, 96, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) positionX, (int) positionY, 96, 16);
    }
}
