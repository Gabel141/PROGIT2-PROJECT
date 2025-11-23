package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;
import framework.render.ModelManager;
import framework.render.Renderer;
import game.entities.Attacker;
import game.entities.Can;
import game.entities.Defender;
import game.entities.Projectile;

public class InGameState extends GameState {

    final List<Projectile> projectiles = new ArrayList<>();

    Attacker player1 = new Attacker(80, 450, 48, 48, 5);
    Defender player2 = new Defender(980, 450, 48, 48, 4);
    Can can1 = new Can(900, 450, 48, 48);

    public boolean p1UP, p1DOWN, p1LEFT, p1RIGHT, p1THROW;
    public boolean p2UP, p2DOWN, p2LEFT, p2RIGHT;

    public InGameState(GameStateManager gsm) {
        super(gsm);
        System.out.println("[GameState][InGameState]: Creating level state...");
    }

    @Override
    public void init() {}

    @Override
    public void tick() {
        if (this.p1UP) {
            player1.moveY(true);
        }
        else if (this.p1DOWN) {
            player1.moveY(false);
        }
        if (this.p1RIGHT) {
            player1.moveX(true);
        }
        else if (this.p1LEFT) {
            player1.moveX(false);
        }

        if (this.p2UP) {
            player2.moveY(true);
        }
        else if (this.p2DOWN) {
            player2.moveY(false);
        }
        if (this.p2RIGHT) {
            player2.moveX(true);
        }
        else if (this.p2LEFT) {
            player2.moveX(false);
        }

        if (p1THROW && player1.getCooldown() <= 0) {
            projectiles.add(new Projectile(player1.getX(), player1.getY(), Color.white));
            player1.setPlayerCooldown(20);
        }

        if (player1.getCooldown() > 0)
            player1.setPlayerCooldown(player1.getCooldown() - 1);

        for(Projectile p: projectiles) {
            p.moveX(true);
        }
    }

    @Override
    public void render(Graphics g) {

        player1.setModel(ModelManager.model(ModelManager.ATTACKER));
        Renderer.renderEntity(player1, g);

        player2.setModel(ModelManager.model(ModelManager.DEFENDER));
        Renderer.renderEntity(player2, g);

        can1.setModel(ModelManager.model(ModelManager.CAN));
        Renderer.renderEntity(can1, g);

        for(Projectile p: projectiles) {
            p.setModel(ModelManager.model(ModelManager.SLIPPER));
            Renderer.renderEntity(p, g);        
        }
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_W) {
            p1UP = true;
        } else if (key == KeyEvent.VK_S) {
            p1DOWN = true;
        }
        if (key == KeyEvent.VK_D) {
            p1RIGHT = true;
        } else if (key == KeyEvent.VK_A) {
            p1LEFT = true;
        }

        if (key == KeyEvent.VK_UP) {
            p2UP = true;
        } else if (key == KeyEvent.VK_DOWN) {
            p2DOWN = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            p2RIGHT = true;
        } else if (key == KeyEvent.VK_LEFT) {
            p2LEFT = true;
        }

        if (key == KeyEvent.VK_SPACE) {
            p1THROW = true;
        }
    }

    @Override
    public void keyReleased(int key) {
        if (key == KeyEvent.VK_W) {
            p1UP = false;
        } else if (key == KeyEvent.VK_S) {
           p1DOWN = false;
        }
        if (key == KeyEvent.VK_D) {
            p1RIGHT = false;
        } else if (key == KeyEvent.VK_A) {
            p1LEFT = false;
        }

        if (key == KeyEvent.VK_UP) {
            p2UP = false;
        } else if (key == KeyEvent.VK_DOWN) {
            p2DOWN = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            p2RIGHT = false;
        } else if (key == KeyEvent.VK_LEFT) {
            p2LEFT = false;
        }

        if (key == KeyEvent.VK_SPACE) {
            p1THROW = false;
        }

    }

    @Override
    public void mouseClicked(int mouse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
}

