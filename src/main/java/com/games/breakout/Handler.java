package com.games.breakout;

import com.games.breakout.gameobjects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    private LinkedList<GameObject> gameObjects = new LinkedList<>();

    public void tick() {
        for (GameObject gameObject : gameObjects) {
            gameObject.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject gameObject : gameObjects) {
            gameObject.render(g);
        }
    }

    public void addObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    public void clear() {
        gameObjects.clear();
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
