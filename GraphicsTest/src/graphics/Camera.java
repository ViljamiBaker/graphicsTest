package graphics;
public class Camera {
    protected Matrix pose;
    public Camera(){
        pose = Matrix.IdentityMatrix;
    }
}
