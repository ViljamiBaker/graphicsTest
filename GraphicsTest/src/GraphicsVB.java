import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import javax.swing.JFrame;

public class GraphicsVB {

    private int[] data;

    private Color currentColor;

    private int xSize;
    private int ySize;

    public GraphicsVB(BufferedImage parent){
        createRaster(parent);
        this.xSize = parent.getWidth();
        this.ySize = parent.getHeight();
        currentColor = Color.RED;
    }

    public GraphicsVB(BufferedImage parent, Color color){
        createRaster(parent);
        this.xSize = parent.getWidth();
        this.ySize = parent.getHeight();
        currentColor = color;
    }

    public void setColor(Color c){
        currentColor = c;
    }

    public void createRaster(BufferedImage parent){
        DataBufferInt dbi = (DataBufferInt)parent.getRaster().getDataBuffer();
        data = dbi.getData();
    }

    public void setPixel(int x, int y){
        data[(x+y*getY())] = currentColor.getRGB();
    }

    public void setRect(int x, int y, int xs, int ys){
        for (int h = 0; h < ys; h++) {
            for (int w = 0; w < xs; w++) {
                setPixel(x+w,y+h);
            }
        }
    }

    public int getX(){
        return xSize;
    }

    public int getY(){
        return ySize;
    }

    public BufferedImage returnEditedImage(){
    int[] bitMasks = new int[]{0xFF0000, 0xFF00, 0xFF, 0xFF000000};
    SinglePixelPackedSampleModel sm = new SinglePixelPackedSampleModel(
            DataBuffer.TYPE_INT, xSize, ySize, bitMasks);
    DataBufferInt db = new DataBufferInt(data, data.length);
    WritableRaster wr = Raster.createWritableRaster(sm, db, new Point());
    BufferedImage image = new BufferedImage(ColorModel.getRGBdefault(), wr, false, null);
    return image;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        Graphics g = jf.getGraphics();
        BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        GraphicsVB gvb = new GraphicsVB(bi);
        gvb.setPixel(100, 100);
        gvb.setRect(100, 100, 10, 10);
        bi = gvb.returnEditedImage();
        try {Thread.sleep(10);}catch(InterruptedException e){}
        g.drawImage(bi, 0, 0, null);
    }
}
