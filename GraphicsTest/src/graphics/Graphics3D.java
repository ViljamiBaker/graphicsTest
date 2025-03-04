package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Graphics3D extends GraphicsVB {

    private Camera camera;

    private V3dObject[] objects;

    private Triangle[] triangles;

    public Graphics3D(int xSize, int ySize, Camera camera){
        super(xSize,ySize, true);
        this.camera = camera;
        this.objects = new V3dObject[]{new V3dObject(new Point3(0, 0, 1))};
        this.triangles = new Triangle[]{new Triangle(new Point3(10, 10, -1),new Point3(10, 0, -1),new Point3(-10, -10, -1), Color.BLACK)};
    }
    private boolean edgeFunction(Point3 p0, Point3 p1, Point3 P){
        return ((P.x - p0.x) * (p1.y - p0.y) - (P.y - p0.y) * (p1.x - p0.x) >= 0);
    }
    public void drawFrame(){
        clear();
        for (Triangle tri : triangles) {
            Point3 p0 = camera.transform(tri.p0);
            Point3 p1 = camera.transform(tri.p1);
            Point3 p2 = camera.transform(tri.p2);
            
            for(int x = 0; x < getX(); x++){
                for(int y = 0; y < getX(); y++){
                    Point3 P = new Point3(x,y,0).multiply(2.0/getX()-1.0);
                    if(edgeFunction(p0, p1, P))setPixel(x, y, Color.RED);
                    if(edgeFunction(p1, p2, P))setPixel(x, y, Color.BLUE);
                    if(edgeFunction(p2, p0, P))setPixel(x, y, Color.GREEN);
                    if(edgeFunction(p0, p1, P)&&edgeFunction(p1, p2, P)&&edgeFunction(p2, p0, P)){
                        setPixel(x, y, tri.color);
                    }
                }
            }
            tri.p1.x++;
            /*
                Point3 p1 = camera.transform(obj.pose);
                Point3 p2 = camera.transform(obj.pose.add(new Point3(0, 3, 0)));
                fillCircle((int)p1.x*getX()+getX()/2, (int)p1.y*getY()+getX()/2, (int)(p2.y-p1.y));
                obj.pose = obj.pose.add(new Point3(0, 0, -0.01));
                System.out.println(p1.z);
            */
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
