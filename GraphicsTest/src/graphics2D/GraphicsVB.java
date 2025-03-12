package graphics2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.util.Random;

import javax.swing.JFrame;

public class GraphicsVB {

    protected int[] data;

    protected Color currentColor;

    protected Color bgColor;

    protected int xSize;
    protected int ySize;

    protected boolean flipped;

    public GraphicsVB(){
        this(100,100, Color.BLACK, false);
    }

    public GraphicsVB(int xSize, int ySize, boolean flipped){
        this(xSize, ySize, Color.BLACK, flipped);
    }

    public GraphicsVB(int xSize, int ySize, Color color, boolean flipped){
        this.xSize = xSize;
        this.ySize = ySize;
        currentColor = color;
        data = new int[xSize*ySize];
        bgColor = Color.WHITE;
        this.flipped = flipped;
    }

    public void setColor(Color c){
        currentColor = c;
    }

    public void setPixel(int x, int y){
        setPixel(x,y,currentColor);
    }

    public void setPixel(int x, int y, Color c){
        if(x<0||x>=xSize||y<0||y>=ySize){
            return;
        }
        data[x+(flipped? getY()-1-y : y)*getY()] = c.getRGB();
    }

    public void fillRect(int x, int y, int xs, int ys){
        for (int h = 0; h < ys; h++) {
            for (int w = 0; w < xs; w++) {
                setPixel(x+w,y+h);
            }
        }
    }

    public void drawTri(int x1, int y1,int x2, int y2,int x3, int y3){
        drawLine(x1,y1,x2,y2);
        drawLine(x2,y2,x3,y3);
        drawLine(x3,y3,x1,y1);
    }

    public void fillTri(int x1, int y1,int x2, int y2,int x3, int y3){
        double l1Length = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        for (double t = 0; t < 1; t+=1/l1Length/3) {
            Vector2D p1 = UtilVB.interpPoints(new Vector2D(x1, y1), new Vector2D(x2,y2), t);
            Vector2D p2 = UtilVB.interpPoints(new Vector2D(x1, y1), new Vector2D(x3,y3), t);
            drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
        }
    }

    public void drawRect(int x, int y, int xs, int ys){
        drawLine(x,y,x,y+ys);
        drawLine(x,y,x+xs,y);
        drawLine(x+xs,y,x+xs,y+ys);
        drawLine(x,y+ys,x+xs,y+ys);
    }
    public void drawLine(int x1, int y1, int x2, int y2){
        int tx1 = Math.min(x1,x2);
        int tx2 = Math.max(x1,x2);
        int ty1 = -1;
        int ty2 = -1;
        if(tx1==x1){
            ty1 = y1;
            ty2 = y2;
        }else{
            ty1 = y2;
            ty2 = y1;
        }
        if(tx2==tx1){
            for (int y = ty1; y < ty2; y++) {
                setPixel(tx1,y);
            }
        }else if(ty1==ty2){
            for (int x = tx1; x < tx2; x++) {
                setPixel(x,ty1);
            }
        }else{
            double m = (ty2-ty1)/(tx2-tx1);
            for (int x = tx1; x < tx2; x++) {
                setPixel(x,(int)(m*(x-tx1)+ty1));
            }
        }
    }
    public void clear()
    {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                setPixel(i, j, bgColor);
            }
        }
    }

    public void drawCircle(int xCenter, int yCenter, int radius)
    {
        circle(xCenter, yCenter, radius, false);
    }

    public void fillCircle(int xCenter, int yCenter, int radius)
    {
        circle(xCenter, yCenter, radius, true);
    }
    private void circle(int centerX, int centerY, int radius, boolean filled)
    {
        int x = 0;
        int y = radius;
        int m = 5 - 4 * radius;

        while (x <= y)
        {
            if(filled){
                drawLine(centerX - x, centerY - y, centerX + x, centerY - y);
                drawLine(centerX - y, centerY - x, centerX + y, centerY - x);
                drawLine(centerX - y, centerY + x, centerX + y, centerY + x);
                drawLine(centerX - x, centerY + y, centerX + x, centerY + y);
            }else{
                setPixel(centerX - x, centerY - y); 
                setPixel(centerX + x, centerY - y);
                setPixel(centerX - y, centerY - x); 
                setPixel(centerX + y, centerY - x);
                setPixel(centerX - y, centerY + x); 
                setPixel(centerX + y, centerY + x);
                setPixel(centerX - x, centerY + y); 
                setPixel(centerX + x, centerY + y);
            }
            if (m > 0)
            {
                y--;
                m -= 8 * y;
            }
            x++;
            m += 8 * x + 4;
        }
    }
    public int getX(){
        return xSize;
    }

    public int getY(){
        return ySize;
    }
    public void forget(int count){
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int x = Math.abs(r.nextInt())%xSize;
            int y = Math.abs(r.nextInt())%ySize;
            data[x+y*getY()] = Color.WHITE.getRGB();
        }
    }

    public BufferedImage returnImage(){
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
        GraphicsVB gvb = new GraphicsVB(1000,1000,false);
        gvb.setColor(Color.BLACK);
        gvb.fillRect(0,0,1000,1000);
        gvb.setColor(Color.BLACK);
        gvb.fillCircle(100, 100, 100);
        gvb.drawCircle(400, 400, 100);
        gvb.drawRect(10, 200, 100, 100);
        gvb.setColor(Color.red);
        gvb.fillRect(400, 100, 100, 100);
        gvb.drawLine(800,800,900,1000);
        gvb.setPixel(769, 664);
        gvb.setColor(Color.GREEN);
        gvb.fillTri(100, 150, 200, 350,150, 600);
        while ( true) {
            //gvb.forget(1000);
            BufferedImage bi = gvb.returnImage();
            g.drawImage(bi, 0, 0, null);
            try {Thread.sleep(10);}catch(InterruptedException e){}
        }
    }
}
