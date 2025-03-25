package graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JFrame;

public class VeyListener implements KeyListener{
    HashSet<Integer> keysHeld = new HashSet<>();
    public VeyListener(){}
    public VeyListener(JFrame f){
        f.addKeyListener(this);
    }
    @Override
    public void keyPressed(KeyEvent e){
        keysHeld.add(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e){
        keysHeld.remove(e.getKeyCode());
    }
    @Override
    public void keyTyped(KeyEvent e){}
    public boolean keyDown(int key){
        return keysHeld.contains(key);
    }
}
