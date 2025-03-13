package Jelly;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.concurrent.TimeUnit;

public class JellyRenderer extends JFrame{
   BufferedImage bi;
   Graphics bg;
   BufferedImage ui;
   Graphics ug;
   Graphics g;
   JellyAi jai;
   int xoffset = 0;
   public JellyRenderer(JellyAi jai){
      this.jai = jai;
      this.setTitle("AI");
      this.setSize(800, 800);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setVisible(true);
      bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
      bg = bi.getGraphics();
      g = this.getGraphics();
      g.setColor(Color.RED);
      g.fillRect(0,0,800,800);
      
   }
   void drawLines(){
      bg.setColor(Color.GRAY);
      for(int i = 0; i<=800; i+=80){
         bg.drawLine(i-xoffset%80, 0,i-xoffset%80,800);
         ug.drawString(String.valueOf(((i+(xoffset-xoffset%80))-200)*2),i-xoffset%80,200);
      }
   }
   static void drawJellyCreature(Graphics bg, Graphics ug, JellyCreature jc, int xoffset){

      
      ug.setColor(Color.BLACK);
      bg.drawLine(200,0,200,800);
      ug.drawString(String.valueOf(jc.getScore()),200,500);
      ug.drawString(String.valueOf(jc.squares[0].index),200,480);
      ug.drawString(String.valueOf(jc.squares[0].springs[0].getLerp()),200,460);
      ug.drawString(String.valueOf(jc.squares[0].springs[0].getLeng()),200,440);
      ug.drawString(String.valueOf(jc.squares[0].springs[0].getStr()),200,420);
      ug.drawString(String.valueOf(jc.lerpTime),200,400);
      ug.drawString(String.valueOf(jc.stateTime),200,380);
      for(SoftSquare s: jc.squares){
         for(Spring spr: s.springs){
            bg.setColor(new Color((float)spr.getDesire(),(float)1.0-(float)spr.getDesire(),(float)spr.getLmult()));
            bg.drawLine((int)(spr.p1.pos.x/2)-xoffset,(int)(spr.p1.pos.y/2),(int)(spr.p2.pos.x/2)-xoffset,(int)(spr.p2.pos.y/2));
         }
      }
   }
   public void drawimg(){
      ui = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
      ug = ui.getGraphics();
      ug.setColor(Color.BLACK);
      bg.setColor(Color.WHITE);
      bg.fillRect(0,0,800,800);
      bg.setColor(Color.BLACK);
      drawLines();
      if(jai.curentDrawCreature!=null){
         if(CONFIG.SCORING_CASE == SCORETYPE.XFAR){
            xoffset = (int)jai.curentDrawCreature.getScore()/2;
         }else{
            xoffset = -200;
         }
         drawJellyCreature(bg,ug,jai.curentDrawCreature,xoffset);
      }
      BufferedImage ai = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
      Graphics ag = ai.getGraphics();
      ag.drawImage(bi,0,800,800,-800,null);
      ag.drawImage(ui,0,0,null);
      g.drawImage(ai,0,0,null);
   }
   long t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
   long[] eta = new long[10];
   public void drawtraining(int index, Generation gen){
      ui = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
      ug = ui.getGraphics();
      ug.setColor(Color.WHITE);
      ug.fillRect(0,0,800,800);
      ug.setColor(Color.BLACK);
      long t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
      long timems = t2-t;
      for(int i = 8; i>=0; i--){
         timems+=eta[i];
         eta[i+1] = eta[i];
      }
      eta[0] = t2-t;
      timems/=10;
      ug.drawString("ETA: " + String.valueOf(timems*(CONFIG.GENERATIONC-index)/1000),250,55);
      t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
      ug.drawString(String.valueOf(index+1) + " / " + CONFIG.GENERATIONC,360,55);
      for(int i = 0; i<gen.scores.length;i++){
         ug.drawString(gen.jcdnas[i].name + " " + String.valueOf((int)gen.scores[i]),120*(i%6)+60,20*(i/6)+100);
      }
      ug.drawRect(80,60,640,20);
      ug.fillRect(80,60,(index+1)*(640/CONFIG.GENERATIONC),20);
      g.drawImage(ui,0,0,null);
   }
}