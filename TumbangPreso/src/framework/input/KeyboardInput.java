package framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public boolean p1UP, p1DOWN, p1LEFT, p1RIGHT, p1THROW;
    public boolean p2UP, p2DOWN, p2LEFT, p2RIGHT;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            p1UP = true;
        }
        if (code == KeyEvent.VK_S) {
            p1DOWN = true;
        }
        if (code == KeyEvent.VK_A) {
            p1LEFT = true;
        }
        if (code == KeyEvent.VK_D) {
            p1RIGHT = true;
        }

        if (code == KeyEvent.VK_UP) {
            p2UP = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            p2DOWN = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            p2LEFT = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            p2RIGHT = true;
        }

        if (code == KeyEvent.VK_SPACE) {
            p1THROW = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            p1UP = false;
        }
        if (code == KeyEvent.VK_S) {
            p1DOWN = false;
        }
        if (code == KeyEvent.VK_A) {
            p1LEFT = false;
        }
        if (code == KeyEvent.VK_D) {
            p1RIGHT = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            p1THROW = false;
        }

        if (code == KeyEvent.VK_UP) {
            p2UP = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            p2DOWN = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            p2LEFT = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            p2RIGHT = false;
        }

    }
}
