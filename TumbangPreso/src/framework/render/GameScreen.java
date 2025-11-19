package framework.render;

import framework.input.KeyboardInput;
import game.entities.Attacker;
import game.entities.Defender;
import game.entities.Projectile;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends JPanel implements Runnable {

    // SCREEN SETTINGS

    final int screenWidth = 1080; // 768 x 576 res
    final int screenHeight = 900;

    int FPS = 60;

    final List<Projectile> projectiles = new ArrayList<>();


    //default player variables

    Attacker player1 = new Attacker(80, 450, 48, 48, 4);
    Defender player2 = new Defender(980, 450, 48, 48, 5);

    public GameScreen() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#b5d4cb"));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(key1);

    }

    // RUNTIME AND TICKS

    static Thread gameThread;

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {

        if (player1.getCooldown() > 0)
            player1.setPlayerCooldown(player1.getCooldown() - 1);

        if (key1.p1UP) {
            player1.moveY(true);
        }
        else if (key1.p1DOWN) {
            player1.moveY(false);
        }
        if (key1.p1RIGHT) {
            player1.moveX(true);
        }
        else if (key1.p1LEFT) {
            player1.moveX(false);
        }

        if (key1.p2UP) {
            player2.moveY(true);
        }
        if (key1.p2DOWN) {
            player2.moveY(false);
        }
        if (key1.p2LEFT) {
            player2.moveX(false);
        }
        if (key1.p2RIGHT) {
            player2.moveX(true);
        }

        if (key1.p1THROW && player1.getCooldown() <= 0) {
            projectiles.add(new Projectile(player1.getX(), player1.getY(), Color.white));
            player1.setPlayerCooldown(20);
        }

        for(Projectile p: projectiles) {
            p.moveX(true);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D p1 = (Graphics2D)g;
        Graphics2D p2 = (Graphics2D)g;

        p1.setColor(Color.white);
        Rectangle p1Box = new Rectangle((int)player1.getX(), (int)player1.getY(), player1.getWidth(), player1.getHeight());
        p1.fillRect((int)player1.getX(), (int)player1.getY(), player1.getWidth(), player1.getHeight());

        p2.setColor(Color.blue);
        p2.fillRect((int)player2.getX(), (int)player2.getY(), player2.getWidth(), player2.getHeight());

        for(Projectile p: projectiles) {
            g.setColor(Color.BLACK);
            g.fillOval((int)p.getX() + 60, (int)p.getY() + 12, 24, 24);
        }
        p1.dispose();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    KeyboardInput key1 = new KeyboardInput();

}
