package graphics;

import java.awt.Color;

public class Triangle {
    Point3 p0;
    Point3 p1;
    Point3 p2;
    Color color;
    public Triangle(Point3 p0, Point3 p1, Point3 p2, Color color){
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }
}
