package graphics2D;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

import java.awt.event.MouseEvent;

public class VouseListener implements MouseListener{
    private boolean mouseDown = false;
    public VouseListener(){}
    public VouseListener(JFrame f){
        f.addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e){
        
    }
    public void mouseExited(MouseEvent e){
        
    }
    public void mouseEntered(MouseEvent e){
        
    }
    public void mouseReleased(MouseEvent e){
        mouseDown = true;
    }
    public void mousePressed(MouseEvent e){
        mouseDown = false;
    }
    public void keyTyped(){}
    public boolean mouseDown(){
        return mouseDown==true;
    }
}
