package framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Game;

public class KeyboardInput implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        Game.getStateManager().keyPressed(code);

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        Game.getStateManager().keyReleased(code);
    }
}
