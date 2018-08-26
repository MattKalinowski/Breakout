package com.games.breakout.userinterface;

import java.awt.*;

public class HUD {
    private int score = 0;
    private int lives = 3;

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Lives: " + lives, 15, 80);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
