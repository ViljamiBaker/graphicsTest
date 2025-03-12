package Game.rendering;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Game.game.Tile;
import Game.game.Map;
import Game.game.Path;
import Game.game.Entity;

import Game.util.Vector2D;
import Game.util.VectorMD;

public class Renderer extends JFrame{
   private Graphics g;
   private Camera camera;
   private Vector2D center = new Vector2D(500,400);
   private Entity[] entities = new Entity[0];
   private Entity[] entitiesToDraw = new Entity[0];
   private Map map;
   public Renderer(Map map){
      this.setTitle("gaming");
      this.setSize(1000,800);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setVisible(true);
      g = this.getGraphics();
      this.camera = new Camera(new Vector2D(0,0), new VectorMD(1,0));
      this.map = map;
   }
   
   private Vector2D convertVec2D(Vector2D initial){
      return initial.add(camera.pos.n()).convert().addD(-camera.rot.D).convert().div(camera.zoom).add(center);
   }
   private void drawLine(Graphics g, double x1, double y1, double x2, double y2){
      drawLine(g, new Vector2D(x1,y1), new Vector2D(x2,y2));
   }
   private void drawLine(Graphics g, Vector2D start, Vector2D end){
      Vector2D startc = convertVec2D(start);
      Vector2D endc = convertVec2D(end);
      g.drawLine((int)startc.x,(int)startc.y,(int)endc.x,(int)endc.y);
   }
   
   private void drawOval(Graphics g, Vector2D pos, Vector2D rad){
      g.drawOval((int)(convertVec2D(pos).x)-(int)(rad.x/camera.zoom)/2,(int)(convertVec2D(pos).y)-(int)(rad.x/camera.zoom)/2,(int)(rad.x/camera.zoom),(int)(rad.y/camera.zoom));
   }
   private void drawRect(Graphics g, Vector2D pos, Vector2D size){
      drawLine(g, pos, pos.addX(size.x));
      drawLine(g, pos, pos.addY(size.y));
      drawLine(g, pos.addY(size.y), pos.add(size));
      drawLine(g, pos.addX(size.x), pos.add(size));
   }
   private Vector2D convSV2D(Graphics g, Sprite s, int index){
      return new Vector2D(s.xposes[index], s.yposes[index]).convert().addD(s.p.rot).convert().add(s.p.pos);
   }
   private void drawSprite(Graphics g, Sprite s){
      int partStart = 0;
      for(int i = 0 ; i<s.xposes.length-1; i++){
         if(s.xposes[i+1]==-1){
            drawLine(g, convSV2D(g, s, i), convSV2D(g, s, partStart));
            partStart = i+2;
            i++;
            continue;
         }
         if(s.xposes[i+1]==-2){
            drawLine(g, convSV2D(g, s, i), convSV2D(g, s, partStart));
            drawOval(g, s.p.pos, new Vector2D(s.yposes[i+1]));
            partStart = i+2;
            i++;
            continue;
         }
         drawLine(g, convSV2D(g, s, i), convSV2D(g, s, i+1));
      }
      drawLine(g, convSV2D(g, s, s.xposes.length-1), convSV2D(g, s, partStart));
   }
   
   public void updateEntities(Entity[] entities){
      this.entities = entities;
   }
   
   public void drawEntity(Graphics bg, Graphics ug, Entity e){
      String strings[] = {
         "path.end" + e.getPath().end,
         "nextTile" + e.getInfo()[0]
      };
      drawLines(ug, strings, convertVec2D(e.getPose().pos.add(new Vector2D(20,20))));
      drawLine(bg, e.getPose().pos, e.getInfo()[1]);
      for(int i = 0; i<e.getPath().lastTiles.length-1; i++){
         Tile tile = e.getPath().lastTiles[i];
         Tile tile2 = e.getPath().lastTiles[i+1];
         drawLine(bg, tile.x*map.squareSize + map.squareSize/2, tile.y*map.squareSize + map.squareSize/2, tile2.x*map.squareSize + map.squareSize/2, tile2.y*map.squareSize + map.squareSize/2);
      }
   }
   
   private void drawLines(Graphics g, String[] lines, Vector2D xy){
      for(int i = 0; i<lines.length;i++){
         g.drawString(lines[i],(int)xy.x,800-(int)xy.y+i*10);
      }
   }
   
   public  void updateEntity(){
      double lowestDist = 10000;
      for(int i = 0; i < entities.length; i++){
         if(convertVec2D(entities[i].getPose().pos).magnitude()<lowestDist){
            entitiesToDraw = entities;
            lowestDist = convertVec2D(entities[i].getPose().pos).magnitude();
         }
      }
      
   }
   
   public void paint(){
      BufferedImage bi = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
      Graphics bg = bi.getGraphics();
      BufferedImage ui = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
      Graphics ug = ui.getGraphics();
      BufferedImage ai = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
      Graphics ag = ai.getGraphics();
      bg.setColor(Color.WHITE);
      bg.fillRect(0,0,1000,800);
      bg.setColor(Color.BLACK);
      ug.setColor(Color.BLACK);
      for(int y = 0; y<map.size; y++){
         for(int x = 0; x<map.size; x++){
            drawRect(bg, new Vector2D(x*map.squareSize,y*map.squareSize), new Vector2D(map.squareSize,map.squareSize));
            //drawLines(ug,new String[]{String.valueOf(map.getTile(x,y).movement)}, convertVec2D(new Vector2D(x*map.squareSize,y*map.squareSize).add(new Vector2D(map.squareSize/2,map.squareSize/2))));
         }
      }
      for(int i = 0; i < entities.length; i++){
         drawSprite(bg, entities[i].s);
      }
      for(int i = 0; i < entitiesToDraw.length; i++){
         //drawEntity(bg, ug, entitiesToDraw[i]);
      }
      ag.drawImage(bi,0,800,1000,-800,null);
      ag.drawImage(ui,0,0,null);
      g.drawImage(ai,0,0,null);
   }
   
   public Camera getCamera(){
      return camera;
   }
   
   public static void main(String args[]){
      Renderer r = new Renderer(new Map(10,10));
      r.paint();
      while(true){
         double t = System.nanoTime()/1000000;
         r.paint();
         r.camera.rot = r.camera.rot.addD(0.01);
         double t2 = System.nanoTime()/1000000;
         try {Thread.sleep((int)Math.max(16-(t2-t),0));}catch(InterruptedException e){}
      }
   }
}