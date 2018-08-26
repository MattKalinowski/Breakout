package com.games.breakout;

import com.games.breakout.gameobjects.Ball;
import com.games.breakout.gameobjects.Block;
import com.games.breakout.gameobjects.Player;
import com.games.breakout.keyboard.KeyInput;
import com.games.breakout.userinterface.HUD;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -6112428091888191314L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    public static final Random RANDOM = new Random();

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        new Window(WIDTH, HEIGHT, "Breakout", this);

        this.addKeyListener(new KeyInput(handler));

        handler.addObject(new Player(270, 400, handler));

        handler.addObject(new Block(100, 100, handler));
        handler.addObject(new Block(164, 100, handler));
        handler.addObject(new Block(228, 100, handler));

        handler.addObject(new Block(100, 150, handler));
        handler.addObject(new Block(164, 150, handler));
        handler.addObject(new Block(228, 150, handler));

        handler.addObject(new Block(100, 50, handler));
        handler.addObject(new Block(164, 50, handler));
        handler.addObject(new Block(228, 50, handler));

        Ball ball = new Ball(310, 383, handler, hud);
        handler.addObject(ball);

    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
