package graphics;
public class Camera {
    protected Matrix pose;
    protected double n;
    protected double f;
    protected double fovV;
    protected double fovH;
    public Camera(){
        pose = Matrix.IdentityMatrix;
        n = 0.1;
        f = 10000;
        fovH = 90;
        fovV = 90;
    }
    public Point3 transform(Point3 p){
        Matrix t = new Matrix(new double[][]
        {
            {1/Math.tan((fovH/2*(Math.PI/180))),0,0,0},
            {0,1/Math.tan((fovH/2*(Math.PI/180))),0,0},
            {0,0,-(f/(f-n)),-1},
            {0,0,-((f*n)/(f-n)),0}
        }
        );
        Point3 pt = p.applyMatrix(t);
        //System.out.println(p.x * t.getData()[0][3] + p.y * t.getData()[1][3] + p.z * t.getData()[2][3] + t.getData()[3][3]);
        return pt;
    }
}
