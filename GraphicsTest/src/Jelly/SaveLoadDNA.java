package Jelly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SaveLoadDNA{
   static void save(DNA dna, double score){
      File datafile = new File("Jelly/savedDNA/" + CONFIG.SCORING_CASE + "_" + (int)score +  "_" + dna.name + ".txt");
      try{
         datafile.createNewFile();
         FileWriter fw = new FileWriter(datafile, true);
         fw.write(dna.name + "\n,");
         fw.write(dna.stateTime + ",");
         fw.write(dna.lerpTime + ",");
         int sl = dna.stiffness.length;
         fw.write(sl + ",");
         int sll = dna.stiffness[0].length;
         fw.write(sll + ",");
         for(int i = 0; i<sl; i++){
            for(int i2 = 0; i2<sll; i2++){
               fw.write(dna.vDesiredLengths[i][i2] + ",");
            }
         }
         for(int i = 0; i<sl; i++){
            for(int i2 = 0; i2<sll; i2++){
               fw.write(dna.hDesiredLengths[i][i2] + ",");
            }
         }
         for(int i = 0; i<sl; i++){
            for(int i2 = 0; i2<sll; i2++){
               fw.write(dna.stiffness[i][i2] + ",");
            }
         }
         fw.write("\n SCORE: " + score);
         fw.write("\n GENERATIONC: " + CONFIG.GENERATIONC);
         fw.write("\n MAX_ITER: " + CONFIG.MAX_ITER);
         fw.write("\n CREATURES: " + CONFIG.CREATURES);
         fw.write("\n DRAG: " + CONFIG.DRAG);
         fw.write("\n GROUND_DRAG: " + CONFIG.GROUND_DRAG);
         fw.write("\n GRAVP: " + CONFIG.GRAVP);
         fw.write("\n FLOOR_HEIGHT: " + CONFIG.FLOOR_HEIGHT);
         fw.write("\n SCORING_CASE: " + CONFIG.SCORING_CASE);
         fw.close(); 
      }catch(IOException e){
         System.out.println(datafile);
         System.out.println(e);
      }
   }
   static DNA load(String name){
      try{
         System.out.println();
         File datafile = new File(System.getProperty("user.dir") + "/src/Jelly/savedDNA/" + name + ".txt");
         Scanner sc = new Scanner(datafile);
         sc.useDelimiter(",");
         String dname = sc.next();
         System.out.println(dname);
         int stateTime = sc.nextInt();
         double lerpTime = Double.valueOf(sc.next());
         int sl = sc.nextInt();
         int sll = sc.nextInt();
         double[][] vDesiredLengths = new double[16][];
         for(int i = 0; i<sl; i++){
            vDesiredLengths[i] = new double[sll];
            for(int i2 = 0; i2<sll; i2++){
               vDesiredLengths[i][i2] = Double.valueOf(sc.next());
            }
         }
         double[][] hDesiredLengths = new double[16][];
         for(int i = 0; i<sl; i++){
            hDesiredLengths[i] = new double[sll];
            for(int i2 = 0; i2<sll; i2++){
               hDesiredLengths[i][i2] = Double.valueOf(sc.next());
            }
         }
         double[][] stiffness = new double[16][];
         for(int i = 0; i<sl; i++){
            stiffness[i] = new double[sll];
            for(int i2 = 0; i2<sll; i2++){
               stiffness[i][i2] = Double.valueOf(sc.next());
            }
         }
         sc.close();
         return new DNA(vDesiredLengths, hDesiredLengths, stiffness, dname, stateTime, lerpTime, 0);
      }catch(FileNotFoundException e){
         System.out.println(e);
      }
      return null;
   }
   public static void main(String[] args){
   }
}