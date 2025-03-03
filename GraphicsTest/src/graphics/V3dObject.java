package graphics;

public class V3dObject {
    Point3 pose;

    public V3dObject(){
        this(new Point3());
    }
    public V3dObject(Point3 pose){
        this.pose = pose;
    }    
}
