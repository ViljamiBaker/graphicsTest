package graphics2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Graphics2V extends GraphicsVB {

    private Camera2D camera;

    private Vector2D center;

    public Graphics2V(int xSize, int ySize, boolean flipped){
        this(xSize,ySize,flipped, new Camera2D(), new Vector2D(0));
    }

    public Graphics2V(int xSize, int ySize, boolean flipped, Camera2D camera, Vector2D center){
        super(xSize,ySize,flipped);
        this.camera = camera;
        this.center = center;
    }

    public Camera2D getCamera(){
        return camera;
    }



    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        Graphics g = jf.getGraphics();
        Graphics2V g2D = new Graphics2V(1000,1000,false);
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0,0,1000,1000);
        g2D.setColor(Color.BLACK);
        g2D.fillCircle(100, 100, 100);
        g2D.drawCircle(400, 400, 100);
        g2D.drawRect(10, 200, 100, 100);
        g2D.setColor(Color.red);
        g2D.fillRect(400, 100, 100, 100);
        g2D.drawLine(800,800,900,1000);
        g2D.setPixel(769, 664);
        while ( true) {
            g2D.forget(1000);
            BufferedImage bi = g2D.returnImage();
            g.drawImage(bi, 0, 0, null);
            try {Thread.sleep(10);}catch(InterruptedException e){}
        }
    }
}
