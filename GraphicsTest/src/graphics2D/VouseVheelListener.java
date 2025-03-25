package graphics2D;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class VouseVheelListener implements MouseWheelListener{
    private double totalWheelMovement = 0.0;
    private double totalRelativeWheelMovement = 0.0;
    private double lastWheelMovement = 0.0;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        totalWheelMovement += e.getPreciseWheelRotation();
        totalRelativeWheelMovement += e.getPreciseWheelRotation();
        lastWheelMovement = e.getPreciseWheelRotation();
    }
    public double getTotalWheelMovement(){
        return totalWheelMovement;
    }
    public double getRelativeWheelMovement(){
        double mov = totalRelativeWheelMovement;
        totalRelativeWheelMovement = 0;
        return mov;
    }
}
