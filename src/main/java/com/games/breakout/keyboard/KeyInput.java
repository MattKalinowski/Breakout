package com.games.breakout.keyboard;

import com.games.breakout.Game;
import com.games.breakout.Handler;
import com.games.breakout.gameobjects.Ball;
import com.games.breakout.gameobjects.GameObject;
import com.games.breakout.gameobjects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    private boolean[] keyDown = new boolean[2];


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject gameObject : handler.getGameObjects()) {

            if (gameObject instanceof Player) {
                if (key == KeyEvent.VK_LEFT) {
                    gameObject.setVelocityX(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    gameObject.setVelocityX(5);
                    keyDown[1] = true;
                }
            }

            if (gameObject instanceof Ball) {
                if (key == KeyEvent.VK_UP) {
                    int direction = Game.RANDOM.nextInt(10);
                    if (direction >= 5) gameObject.setVelocityX(-3);
                    else gameObject.setVelocityX(3);
                    gameObject.setVelocityY(-5);
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject gameObject : handler.getGameObjects()) {
            if (gameObject instanceof Player) {
                if (key == KeyEvent.VK_LEFT) { keyDown[0] = false; }
                if (key == KeyEvent.VK_RIGHT) { keyDown[1] = false; }

                if (!keyDown[0] && !keyDown[1]) gameObject.setVelocityX(0);
            }
        }
    }
}
