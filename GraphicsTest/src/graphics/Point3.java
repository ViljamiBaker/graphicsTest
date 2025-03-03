package graphics;

public class Point3 {
    double x;
    double y;
    double z;
    public Point3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Matrix toMatrix(){
        return new Matrix(new double[][]{{x,y,z,1}});
    }
    public static Point3 fromMatrix(Matrix m1){
        return new Point3().applyMatrix(m1);
    }

    public Point3 applyMatrix(Matrix m1){
        double[][] d = m1.getData();
        double x1 = d[0][0] * this.x + d[1][0] * this.y + d[2][0] * this.z + d[3][0];
        double y1 = d[0][1] * this.x + d[1][1] * this.y + d[2][1] * this.z + d[3][1];
        double z1 = d[0][2] * this.x + d[1][2] * this.y + d[2][2] * this.z + d[3][2];
        double w = this.x * d[0][3] + this.y * d[1][3] + this.z * d[2][3] + d[3][3];
        x1/=w;
        y1/=w;
        z1/=w;
        return new Point3(x1, y1, z1);
    }

    public double magnitude(){
        return Math.sqrt(x*x+y*y+z*z);
    }

    public Point3 add(Point3 other){
        return new Point3(this.x + other.x,this.y + other.y,this.z + other.z);
    }

    public Point3 multiply(double d){
        return new Point3(this.x * d,this.y * d,this.z * d);
    }

    public Point3 n(){
        return new Point3(this.x * -1,this.y * -1,this.z * -1);
    }
}
