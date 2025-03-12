package graphics2D;

import java.awt.Graphics;

import javax.swing.JFrame;

public class VFrame extends JFrame{
    private Graphics2V g2v;
    private Graphics g;
    private Camera2D camera;
    private Vector2D center;
    public VFrame(int sizex, int sizey, String name){
        this.setTitle(name);
        this.setSize(sizex,sizey);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.g = this.getGraphics();
        this.camera = new Camera2D(new Vector2D(0,0), new VectorMD(1,0));
        this.center = new Vector2D(sizex/2.0, sizey/2.0);
        this.g2v = new Graphics2V(sizex, sizey, true, camera, center);
    }
    public Graphics2V getGraphics2v(){
        return g2v;
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(g2v.returnImage(), 0, 0, null);
    }
}
