package Jelly;
public class CONFIG{
   public static int GENERATIONC = 20;
   public static int MAX_ITER = 1000;
   public static int CREATURES = 100;
   
   public static double DRAG = 0.99;
   public static double GROUND_DRAG = 0.1;
   public static double GRAVP = -0.3;
   public static double FLOOR_HEIGHT = 30;
   
   public static double LMAX = 1.5;
   public static double LMIN = 0.5;
   
   public static double SMAX = 1.5;
   public static double SMIN = 0.5;
   
   public static int STATE_MAX = 200;
   public static int STATE_MIN = 20;
   
   public static double MAXMUTATE = 1.0;
   public static double MAXBIGMUTATE = 20.0;
   
   public static double MUTATE_CHANCE = 0.1;
   
   public static double BIG_MUTATE_CHANCE = 0.50;
   
   public static SCORETYPE SCORING_CASE = SCORETYPE.XFAR;
   
   public static boolean DUMP_FULL_DNA = false;
   
   public static void main(String[] args){
      //LoadConfig.loadConfig();
      JellyAi jai = new JellyAi();
      jai.fullSim();
      //jai.simName("XFAR_1117_NYBRUHW^.274");
   }
}