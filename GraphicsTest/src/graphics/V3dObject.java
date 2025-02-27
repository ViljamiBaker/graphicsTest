package graphics;

public class V3dObject {
    Matrix pose;

    public V3dObject(){
        this(Matrix.IdentityMatrix);
    }
    public V3dObject(Matrix pose){
        this.pose = pose;
    }    
}
