package graphics;

public class Point {
    double x;
    double y;
    double z;
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Matrix toMatrix(){
        return new Matrix(new double[][]{{x,y,z,1}});
    }
    public static Point fromMatrix(Matrix m1){
        double[][] d = m1.getData();
        double x1 = d[3][0];
        double y1 = d[3][1];
        double z1 = d[3][2];
        return new Point(x1, y1, z1);
    }
}
