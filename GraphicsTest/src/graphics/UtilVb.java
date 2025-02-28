package graphics;
// https://realityserver.com/articles/3d-transformations-part1-matrices/
// https://learn.microsoft.com/en-us/windows/win32/direct3d9/transforms
public class UtilVb {
    private static Matrix translateMatrix(double x, double y, double z){
        Matrix m2 = new Matrix(4, 4);
        m2.setData(3, 0, -x);
        m2.setData(3, 1, -y);
        m2.setData(3, 2, -z);
        return m2;
    }
    private static Matrix rotateMatrix(double x, double y, double z, double theta){
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
        return m2;
    }
    private static Matrix scaleMatrix(double x, double y, double z){
        Matrix m2 = Matrix.IdentityMatrix;
        m2.setData(0, 0, 1/x);
        m2.setData(1, 1, 1/y);
        m2.setData(2, 2, 1/z);
        return m2;
    }
    public static Matrix translate(Matrix m1, double x, double y, double z){
        Matrix m2 = translateMatrix(x, y, z);
        return m1.add(m2);
    }
    public static Matrix rotate(Matrix m1, double x, double y, double z, double theta){
        Matrix m2 = rotateMatrix(x, y, z, theta);
        return m1.add(m2);
    }
    public static Matrix scale(Matrix m1, double x, double y, double z){
        Matrix m2 = scaleMatrix(x, y, z);
        return m1.add(m2);
    }
    public static Point applyMatrix(Point p1, Matrix m1){
        double[][] d = m1.getData();
        Point p2 = new Point(
            (p1.x*d[0][0])+(p1.x*d[1][0])+(p1.x*d[2][0])+(d[3][0]), 
            (p1.y*d[0][1])+(p1.y*d[1][1])+(p1.y*d[2][1])+(d[3][1]),  
            (p1.z*d[0][2])+(p1.z*d[1][2])+(p1.z*d[2][2])+(d[3][2])
        );
        return Point.fromMatrix(p1.toMatrix().multiply(m1));
    }
    public static Matrix createTranslateMatrix(Point txyz){
        return translateMatrix(txyz.x, txyz.y, txyz.z);
    }
    public static Matrix createScaleMatrix(Point scale){
        return scaleMatrix(scale.x, scale.y, scale.z);
    }
    public static Matrix createRotateMatrix(Point axis, double theta){
        return rotateMatrix(axis.x, axis.y, axis.z, theta);
    }
}
