package com.games.breakout.gameobjects;

import com.games.breakout.Game;
import com.games.breakout.Handler;
import com.games.breakout.userinterface.HUD;

import java.awt.*;

public class Ball extends GameObject {

    private HUD hud;

    private boolean inMove = false;

    public Ball(float positionX, float positionY, Handler handler, HUD hud) {
        super(positionX, positionY, handler);
        this.hud = hud;
    }

    @Override
    public void tick() {
        if (velocityX == 0 && velocityY == 0) {
            followThePlayer();
        } else {
            inMove = true;
        }
        positionX += velocityX;
        positionY += velocityY;
        collision();

        if (positionY >= Game.HEIGHT) {
            handler.removeObject(this);
            hud.setLives(hud.getLives() - 1);
        }
    }

    private void followThePlayer() {
        for (GameObject gameObject : handler.getGameObjects()) {
            if (gameObject instanceof Player) {
                positionX = gameObject.getPositionX() + 40;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) positionX, (int) positionY, 16, 16);
    }

    private void collision() {
        if (positionY <= 0) {
            //AudioPlayer.getSound("pop").play();
            velocityY *= -1;
        }
        if (positionX <= 0 || positionX >= Game.WIDTH - 16) {
            //AudioPlayer.getSound("pop2").play();
            velocityX *= -1;
        }
        GameObject block = null;
        for (GameObject gameObject : handler.getGameObjects()) {
            if (gameObject instanceof Player) {
                if (gameObject.getBounds().intersects(this.getBounds())) {
                    velocityY *= -1;
                    if (gameObject.getPositionX() - 16 <= positionX && positionX < gameObject.getPositionX() + 48) {
                        if (velocityX > 0) {
                            System.out.println("LEFT");
                            velocityX *= -1;
                        }
                    } else if (gameObject.getPositionX() + 48 < positionX && positionX < gameObject.getPositionX() + 96) {
                        if (velocityX < 0) {
                            System.out.println("RIGHT");
                            velocityX *= -1;
                        }
                    }
                }
            }
            if (gameObject instanceof Block) {
                if (gameObject.getBounds().intersects(this.getBounds())) {
                    velocityY *= -1;
                    block = gameObject;
                }
            }
        }
        if (block != null) {
            handler.removeObject(block);
            hud.setScore(hud.getScore() + 20);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) positionX, (int) positionY, 16, 16);
    }

    public boolean isInMove() {
        return inMove;
    }

    public void setInMove(boolean inMove) {
        this.inMove = inMove;
    }
}
