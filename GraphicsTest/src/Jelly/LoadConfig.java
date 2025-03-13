package Jelly;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class LoadConfig{
   static String loadValue(Scanner sc){
      sc.next();
      sc.next();
      sc.next();
      return sc.next();
   }
   static void loadConfig(){
      try{
         File datafile = new File(System.getProperty("user.dir") + "/src/Jelly/config.txt");
         Scanner sc = new Scanner(datafile);
         sc.useDelimiter(" ");
         sc.nextLine();
         sc.nextLine();
         CONFIG.GENERATIONC = Integer.parseInt(loadValue(sc));
         CONFIG.MAX_ITER = Integer.parseInt(loadValue(sc));
         CONFIG.CREATURES = Integer.parseInt(loadValue(sc));
         CONFIG.DRAG = Double.valueOf(loadValue(sc));
         CONFIG.GROUND_DRAG = Double.valueOf(loadValue(sc));
         CONFIG.GRAVP = Double.valueOf(loadValue(sc));
         CONFIG.FLOOR_HEIGHT = Double.valueOf(loadValue(sc));
         CONFIG.LMAX = Double.valueOf(loadValue(sc));
         CONFIG.LMIN = Double.valueOf(loadValue(sc));
         CONFIG.SMAX = Double.valueOf(loadValue(sc));
         CONFIG.SMIN = Double.valueOf(loadValue(sc));
         CONFIG.STATE_MAX = Integer.parseInt(loadValue(sc));
         CONFIG.STATE_MIN = Integer.parseInt(loadValue(sc));
         CONFIG.MAXMUTATE = Double.valueOf(loadValue(sc));
         CONFIG.MAXBIGMUTATE = Double.valueOf(loadValue(sc));
         CONFIG.MUTATE_CHANCE = Double.valueOf(loadValue(sc));
         CONFIG.BIG_MUTATE_CHANCE = Double.valueOf(loadValue(sc));
         CONFIG.SCORING_CASE = SCORETYPE.valueOf(loadValue(sc));
         CONFIG.DUMP_FULL_DNA = Boolean.valueOf(loadValue(sc));
         sc.close();
      }catch(FileNotFoundException e){
         System.out.println(e);
      }
   }
}