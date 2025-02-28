package graphics;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Graphics3D extends GraphicsVB {

    private Camera camera;

    private Matrix worldMatrix;

    private V3dObject[] objects;

    public Graphics3D(int xSize, int ySize, Camera camera){
        super(xSize,ySize);
        this.camera = camera;
        this.objects = new V3dObject[]{new V3dObject(Matrix.matfrompos(0, 0, 5))};
        this.worldMatrix = Matrix.IdentityMatrix;
    }
    public void drawFrame(){
        clear();
        for (V3dObject obj : objects) {
            Point p = Point.fromMatrix(obj.pose.multiply(camera.pose.scalarMult(-1)));
            double pprimex = p.x/p.z;
            double pprimey = p.y/p.z;
            Point p2 = Point.fromMatrix(obj.pose.add(UtilVb.createTranslateMatrix(new Point(1000, 0, 0))).multiply(camera.pose.scalarMult(-1)));
            double pprimex2 = p2.x/p2.z;
            double pprimey2 = p2.y/p2.z;
            fillCircle((int)pprimex, (int)pprimey, (int)Math.sqrt(Math.pow(pprimex-pprimex2,2) + Math.pow(pprimey-pprimey2,2)));
            obj.pose = obj.pose.add(UtilVb.createTranslateMatrix(new Point(0, 0, -0.01)));
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
            g.drawImage(g3d.returnImage(), 0, 0, jf);
            try {Thread.sleep(10);}catch(InterruptedException e){}
        }
    }
}
