package Jelly;

import java.util.concurrent.TimeUnit;

public class JellyAi{
   JellyRenderer jr;
   JellyCreature curentDrawCreature = null;
   public JellyAi(){
      jr = new JellyRenderer(this);
   }
   static public JellyCreature genJellyCreature(){
      Point[][] points = new Point[5][];
      int xoffset = 200;
      int yoffset = 200;
      for(int y = 0; y<5; y++){
         points[y] = new Point[5];
         for(int x = 0; x<5; x++){
            Point p = new Point(new Vector2D(x*100+xoffset,y*100+yoffset));
            points[y][x] = p;
         }
      }
      double lerpTime = Math.random()*0.8+0.2;
      int stateTime = (int)(Math.random()*60)+40;
      Spring[][][] cubeSprings = new Spring[4][][];
      for(int x = 0; x<4; x++){
         cubeSprings[x] = new Spring[4][];
         for(int y = 0; y<4; y++){
            cubeSprings[x][y] = new Spring[6];//{vertical right, vertical left, horizontal up, horizontal down, (ul -> br), (ur -> bl)}
            cubeSprings[x][y][0] = new Spring(points[y][x], points[y+1][x], 1, lerpTime, stateTime);
            cubeSprings[x][y][1] = new Spring(points[y][x+1], points[y+1][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][2] = new Spring(points[y][x], points[y][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][3] = new Spring(points[y+1][x], points[y+1][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][4] = new Spring(points[y+1][x], points[y][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][5] = new Spring(points[y+1][x+1], points[y][x], 1, lerpTime, stateTime);
         }
      }
      SoftSquare[][] squares = new SoftSquare[4][];
      for(int x = 0; x<4; x++){
         squares[x] = new SoftSquare[4];
         for(int y = 0; y<4; y++){
            squares[x][y] = new SoftSquare(cubeSprings[x][y],
            new double[] {Math.random()*(CONFIG.LMAX-CONFIG.LMIN) + CONFIG.LMIN,Math.random()*(CONFIG.LMAX-CONFIG.LMIN) + CONFIG.LMIN,Math.random()*(CONFIG.LMAX-CONFIG.LMIN) + CONFIG.LMIN},
            new double[] {Math.random()*(CONFIG.LMAX-CONFIG.LMIN) + CONFIG.LMIN,Math.random()*(CONFIG.LMAX-CONFIG.LMIN) + CONFIG.LMIN,Math.random()*(CONFIG.LMAX-CONFIG.LMIN) + CONFIG.LMIN}, 
            new double[] {Math.random()*(CONFIG.SMAX-CONFIG.SMIN) + CONFIG.SMIN,Math.random()*(CONFIG.SMAX-CONFIG.SMIN) + CONFIG.SMIN,Math.random()*(CONFIG.SMAX-CONFIG.SMIN) + CONFIG.SMIN});
         }
      }
      SoftSquare[] squarelong = new SoftSquare[16];
      for(int x = 0; x<4; x++){
         for(int y = 0; y<4; y++){
            squarelong[x + y*4] = squares[x][y];
         }
      }
      Point[] pointslong = new Point[25];
      for(int x = 0; x<5; x++){
         for(int y = 0; y<5; y++){
            pointslong[x + y*5] = points[x][y];
         }
      }
      JellyCreature lilguy = new JellyCreature(squarelong,pointslong, JellyCreature.genName(), stateTime, lerpTime, 0);
      return lilguy;
   }
   static public JellyCreature buildWithDNA(DNA dna){
      Point[][] points = new Point[5][];
      int xoffset = 200;
      int yoffset = 200;
      for(int y = 0; y<5; y++){
         points[y] = new Point[5];
         for(int x = 0; x<5; x++){
            Point p = new Point(new Vector2D(x*100+xoffset,y*100+yoffset));
            points[y][x] = p;
         }
      }
      double lerpTime = dna.lerpTime;
      int stateTime = dna.stateTime;
      Spring[][][] cubeSprings = new Spring[4][][];
      for(int x = 0; x<4; x++){
         cubeSprings[x] = new Spring[4][];
         for(int y = 0; y<4; y++){
            cubeSprings[x][y] = new Spring[6];//{vertical right, vertical left, horizontal up, horizontal down, (ul -> br), (ur -> bl)}
            cubeSprings[x][y][0] = new Spring(points[y][x], points[y+1][x], 1, lerpTime, stateTime);
            cubeSprings[x][y][1] = new Spring(points[y][x+1], points[y+1][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][2] = new Spring(points[y][x], points[y][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][3] = new Spring(points[y+1][x], points[y+1][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][4] = new Spring(points[y+1][x], points[y][x+1], 1, lerpTime, stateTime);
            cubeSprings[x][y][5] = new Spring(points[y+1][x+1], points[y][x], 1, lerpTime, stateTime);
         }
      }
      SoftSquare[][] squares = new SoftSquare[4][];
      for(int x = 0; x<4; x++){
         squares[x] = new SoftSquare[4];
         for(int y = 0; y<4; y++){
            squares[x][y] = new SoftSquare(cubeSprings[x][y], dna.getDNAs()[0][x + y*4], dna.getDNAs()[1][x + y*4], dna.getDNAs()[2][x + y*4]);
         }
      }
      SoftSquare[] squarelong = new SoftSquare[16];
      for(int x = 0; x<4; x++){
         for(int y = 0; y<4; y++){
            squarelong[x + y*4] = squares[x][y];
         }
      }
      Point[] pointslong = new Point[25];
      for(int x = 0; x<5; x++){
         for(int y = 0; y<5; y++){
            pointslong[x + y*5] = points[x][y];
         }
      }
      JellyCreature lilguy = new JellyCreature(squarelong,pointslong, dna.name, dna.stateTime,dna.lerpTime, dna.generation);
      return lilguy;
   }
   
   static public void updateCreature(int i, JellyCreature jc){
      if(i%jc.stateTime==jc.stateTime-1){
         jc.updateSquares();
      }
      jc.update();
   }
   
   static public double simCreature(JellyCreature OG, int maxindex){
      JellyCreature jc = buildWithDNA(new DNA(OG));
      for(int i = 0; i<maxindex; i++){
         updateCreature(i,jc);
      }
      return jc.getScore();
   }
   
   public double drawSimCreature(JellyCreature OG, int maxindex){
      JellyCreature jc = buildWithDNA(new DNA(OG));
      this.curentDrawCreature = jc;
      for(int i = 0; i<maxindex; i++){
         long t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
         jr.drawimg();
         updateCreature(i,jc);
         long t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
         try {Thread.sleep((int)Math.max(16-(t2-t),0));}catch(InterruptedException e){}
      }
      this.curentDrawCreature = null;
      return jc.getScore();
   }
   public void fullSim(){
      Generation gen = Generation.runNewGeneration(CONFIG.CREATURES,CONFIG.MAX_ITER);
      for(int i = 0; i<CONFIG.GENERATIONC; i++){
         gen.evolve();
         gen.runGeneration(CONFIG.MAX_ITER);
         jr.drawtraining(i , gen);
         DNA best = gen.getBest();
         System.out.println(simCreature(buildWithDNA(best),CONFIG.MAX_ITER));
         System.out.println("Done " + i);
      }
      DNA best = gen.getBest();
      System.out.println(best.name);
      if(CONFIG.DUMP_FULL_DNA){
         for(int i = 0; i<gen.scores.length; i++){
            SaveLoadDNA.save(gen.jcdnas[i], gen.scores[i]);
         }
      }else{
         SaveLoadDNA.save(best, gen.scores[0]);
         SaveLoadDNA.save(gen.jcdnas[gen.jcdnas.length-1], gen.scores[gen.scores.length-1]);
      }
      new IJellyRenderer(gen.jcdnas[gen.jcdnas.length-1],"WORST").start();
      new IJellyRenderer(best,"BEST").start();
   }
   public void simName(String creature){
      DNA creatureDNA = SaveLoadDNA.load(creature);
      new IJellyRenderer(creatureDNA, "SIM").start();
   }
   public static void main(String[] args){
      JellyAi jai = new JellyAi();
      jai.fullSim();
      /*Generation gen = Generation.runNewGeneration(100,1000);
      DNA best = gen.getBest();
      System.out.println(jai.simCreature(jai.buildWithDNA(best),1000));
      jai.drawSimCreature(jai.buildWithDNA(best),1000);
      System.out.println("Done");*/
   }
}