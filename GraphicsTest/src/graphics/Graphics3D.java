package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Graphics3D extends GraphicsVB {

    Camera camer;

    public Graphics3D(int xSize, int ySize, Camera camera){
        this.xSize = xSize;
        this.ySize = ySize;
        this.currentColor = Color.black;
        this.data = new int[xSize*ySize];
    }
    public static void main(String[] args) {
                JFrame jf = new JFrame();
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        Graphics g = jf.getGraphics();
        Graphics3D gvb = new Graphics3D(1000,1000, new Camera());
        gvb.setColor(Color.BLACK);
        gvb.fillRect(0,0,1000,1000);
        gvb.setColor(Color.BLACK);
        gvb.fillCircle(100, 100, 100);
        gvb.drawCircle(400, 400, 100);
        gvb.drawRect(10, 200, 100, 100);
        gvb.setColor(Color.red);
        gvb.fillRect(400, 100, 100, 100);
        gvb.drawLine(800,800,900,1000);
        gvb.setPixel(769, 664);
        while ( true) {
            gvb.forget(1000);
            BufferedImage bi = gvb.returnImage();
            g.drawImage(bi, 0, 0, null);
            try {Thread.sleep(10);}catch(InterruptedException e){}
        }
    }
}
