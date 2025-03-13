package Jelly;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.concurrent.TimeUnit;

public class IJellyRenderer extends Thread{
   DNA dna;
   String name;
   public IJellyRenderer(DNA dna, String name){
      this.dna=dna;
      this.name = name;
   }
   public class SmallJellyRenderer extends JFrame{
      BufferedImage bi;
      Graphics bg;
      BufferedImage ui;
      Graphics ug;
      Graphics g;
      JellyAi jai;
      int xoffset = 0;
      public SmallJellyRenderer(){
         this.jai = jai;
         this.setTitle(name + " " + dna.name);
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
      public void drawimg(JellyCreature jc){
      ui = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
      ug = ui.getGraphics();
      ug.setColor(Color.BLACK);
      bg.setColor(Color.WHITE);
      bg.fillRect(0,0,800,800);
      bg.setColor(Color.BLACK);
      drawLines();
      if(CONFIG.SCORING_CASE == SCORETYPE.XFAR){
         xoffset = (int)jc.getScore()/2;
      }else{
         xoffset = -200;
      }
      JellyRenderer.drawJellyCreature(bg,ug,jc,xoffset);
      BufferedImage ai = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
      Graphics ag = ai.getGraphics();
      ag.drawImage(bi,0,800,800,-800,null);
      ag.drawImage(ui,0,0,null);
      g.drawImage(ai,0,0,null);
   }
   }
   public double drawSimCreature(){
      SmallJellyRenderer jr = new SmallJellyRenderer();
      JellyCreature jc = JellyAi.buildWithDNA(dna);
      for(int i = 0; i<CONFIG.MAX_ITER; i++){
         long t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
         jr.drawimg(jc);
         JellyAi.updateCreature(i,jc);
         long t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
         try {Thread.sleep((int)Math.max(16-(t2-t),0));}catch(InterruptedException e){}
      }
      return jc.getScore();
   }
   public void run(){
      drawSimCreature();
   }
}