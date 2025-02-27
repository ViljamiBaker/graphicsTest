package graphics;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Graphics3D extends GraphicsVB {

    Camera camera;

    V3dObject[] objects;

    public Graphics3D(int xSize, int ySize, Camera camera){
        super(xSize,ySize);
        this.camera = camera;
        this.objects = new V3dObject[]{new V3dObject(Matrix.matfrompos(1, 1, 1))};
    }
    public void drawFrame(){
        for (V3dObject obj : objects) {
            
        }
    }
    public static void main(String[] args) {
                JFrame jf = new JFrame();
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        Graphics g = jf.getGraphics();
        Graphics3D g3d = new Graphics3D(1000,1000, new Camera());
        while ( true) {
            g3d.drawFrame();
            try {Thread.sleep(10);}catch(InterruptedException e){}
        }
    }
}
