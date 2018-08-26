package com.games.breakout.gameobjects;

import com.games.breakout.Handler;

import java.awt.*;

public abstract class GameObject {

    protected float positionX, positionY;
    protected float velocityX, velocityY;
    protected Handler handler;

    public GameObject(float positionX, float positionY, Handler handler) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
