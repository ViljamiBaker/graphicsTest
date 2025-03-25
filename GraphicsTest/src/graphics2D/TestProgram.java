package graphics2D;

public class TestProgram {
    public static void main(String[] args) {
        VFrame vf = new VFrame(800, 800, "test");
        Graphics2V g2v = vf.getGraphics2v();
        Camera2D c = g2v.getCamera();
        c.move(true, false, false, true, false, false, false, false, 0, 0, 0);
    }
}
