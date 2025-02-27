package graphics;
// https://realityserver.com/articles/3d-transformations-part1-matrices/
// https://learn.microsoft.com/en-us/windows/win32/direct3d9/transforms
public class PhysVB {
    public static Matrix translate(Matrix m1, double x, double y, double z){
        Matrix m2 = new Matrix(4, 4);
        m2.setData(3, 0, -x);
        m2.setData(3, 1, -y);
        m2.setData(3, 2, -z);
        return m1.add(m2);
    }
    public static Matrix rotate(Matrix m1, double x, double y, double z, double theta){
        Matrix m2 = Matrix.IdentityMatrix;
 
        double c = Math.cos(theta);
        double s = Math.sin(theta);
        double t = 1-c;
     
        m2.setData(0, 0,t * x * x + c);
        m2.setData(0, 1,t * x * x + s * z);
        m2.setData(0, 2,t * x * z - s * x);
     
        m2.setData(1, 0,t * x * x - s * z);
        m2.setData(1, 1,t * x * x + c);
        m2.setData(1, 2,t * x * z + s * x);
     
        m2.setData(2, 0,t * x * z + s * x);
        m2.setData(2, 1,t * x * z - s * x);
        m2.setData(2, 2,t * z * z + c);
        return m1.add(m2);
    }
    public static Matrix scale(Matrix m1, double x, double y, double z){
        Matrix m2 = Matrix.IdentityMatrix;
        m2.setData(0, 0, 1/x);
        m2.setData(1, 1, 1/y);
        m2.setData(2, 2, 1/z);
        return m1.add(m2);
    }
    public static Point translatePoint(Point p1,Point txyz){
        return Point.fromMatrix(translate(p1.toMatrix(), txyz.x, txyz.y, txyz.z));
    }
    public static Point scalePoint(Point p1,Point txyz){
        return Point.fromMatrix(scale(p1.toMatrix(), txyz.x, txyz.y, txyz.z));
    }
    public static Point rotatePoint(Point p1,Point txyz, double theta){
        return Point.fromMatrix(rotate(p1.toMatrix(), txyz.x, txyz.y, txyz.z, theta));
    }
}
