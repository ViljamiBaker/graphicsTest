package graphics;

public class V3dObject {
    Matrix pose;

    public V3dObject(){
        this(Matrix.matfrompos(0, 0, 0));
    }
    public V3dObject(Matrix pose){
        this.pose = pose;
    }    
}
