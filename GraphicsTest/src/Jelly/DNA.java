package Jelly;

public class DNA implements Cloneable{
   double[][] vDesiredLengths;
   double[][] hDesiredLengths;
   double[][] stiffness;
   int stateTime;
   double lerpTime;
   String name;
   int generation = 0;
   public DNA(double[][] vDesiredLengths, double[][] hDesiredLengths, double[][] stiffness, String name, int stateTime, double lerpTime, int generation){
      this.vDesiredLengths = vDesiredLengths;
      this.hDesiredLengths = hDesiredLengths;
      this.stiffness = stiffness;
      this.name = name;
      this.stateTime = stateTime;
      this.lerpTime = lerpTime;
      this.generation = generation;
   }
   public DNA(int l, int s , int h, int v, String name){
      this.stiffness = new double[l][];
      this.hDesiredLengths = new double[l][];
      this.vDesiredLengths = new double[l][];
      for(int i = 0; i<l; i++){
         this.stiffness[i]= new double[s];
         this.hDesiredLengths[i] = new double[h];
         this.vDesiredLengths[i] = new double[v];
      }
      this.name = name;
      this.stateTime = 100;
      this.lerpTime = 0.5;
   }
   public DNA(String name){
      this.stiffness = new double[16][];
      this.hDesiredLengths = new double[16][];
      this.vDesiredLengths = new double[16][];
      for(int i =0; i<16; i++){
      stiffness[i] = new double[3];
      for(int i2 =0; i2<3; i2++){
         stiffness[i][i2] = 1;
      }
      vDesiredLengths[i] = new double[3];
      for(int i2 =0; i2<3; i2++){
         vDesiredLengths[i][i2] = 1;
      }
      hDesiredLengths[i] = new double[3];
      for(int i2 =0; i2<3; i2++){
         hDesiredLengths[i][i2] = 1;
      }
      this.stateTime = 100;
      this.lerpTime = 0.5;
      this.name = name;
   }
}
   public DNA(JellyCreature jc){
      stiffness = new double[16][];
      hDesiredLengths = new double[16][];
      vDesiredLengths = new double[16][];
      for(int i =0; i<jc.squares.length; i++){
         stiffness[i] = new double[jc.squares[i].stiffness.length];
         for(int i2 =0; i2<jc.squares[i].stiffness.length; i2++){
            stiffness[i][i2] = jc.squares[i].stiffness[i2];
         }
         vDesiredLengths[i] = new double[jc.squares[i].vDesiredLengths.length];
         for(int i2 =0; i2<jc.squares[i].vDesiredLengths.length; i2++){
            vDesiredLengths[i][i2] = jc.squares[i].vDesiredLengths[i2];
         }
         hDesiredLengths[i] = new double[jc.squares[i].hDesiredLengths.length];
         for(int i2 =0; i2<jc.squares[i].hDesiredLengths.length; i2++){
            hDesiredLengths[i][i2] = jc.squares[i].hDesiredLengths[i2];
         }
      }
      this.stateTime = jc.stateTime;
      this.lerpTime = jc.lerpTime;
      this.name = jc.name;
      this.generation = jc.generation;
    }
   @Override
   protected Object clone() throws CloneNotSupportedException {
   
       DNA clone = new DNA(vDesiredLengths.length,stiffness[0].length,hDesiredLengths[0].length,vDesiredLengths[0].length, this.name);
       clone.generation = this.generation++;
       for(int i =0; i<vDesiredLengths.length; i++){
         for(int i2 =0; i2<stiffness[i].length; i2++){
            clone.stiffness[i][i2] = stiffness[i][i2];
         }
         for(int i2 =0; i2<vDesiredLengths[i].length; i2++){
            clone.vDesiredLengths[i][i2] = vDesiredLengths[i][i2];
         }
         for(int i2 =0; i2<hDesiredLengths[i].length; i2++){
            clone.hDesiredLengths[i][i2] = hDesiredLengths[i][i2];
         }
      }
      return clone;
   }
   public double[][][] getDNAs(){
      return new double[][][] {vDesiredLengths,hDesiredLengths,stiffness};
   }
   static String chars = "QWERTYUIOPASDFGHJKLZXCVBNM";
   public DNA mutate(){
      DNA newDNA = null;
      try{
         newDNA = (DNA)this.clone();
      }catch(CloneNotSupportedException e){}
      boolean mutated = false;
      if(Math.random()<CONFIG.BIG_MUTATE_CHANCE){
         mutated = true;
         newDNA.stateTime += (int)(Math.random()*CONFIG.MAXBIGMUTATE)-(CONFIG.MAXBIGMUTATE/2);
         if(newDNA.stateTime>CONFIG.STATE_MAX){
            newDNA.stateTime=CONFIG.STATE_MAX;
         }else if(newDNA.stateTime<CONFIG.STATE_MIN){
            newDNA.stateTime=CONFIG.STATE_MIN;
         }
      }
      if(Math.random()<CONFIG.BIG_MUTATE_CHANCE){
         mutated = true;
         newDNA.lerpTime += Math.random()*CONFIG.MAXMUTATE - CONFIG.MAXMUTATE/2;
         if(newDNA.lerpTime>1.0){
            newDNA.lerpTime=1.0;
         }else if(newDNA.lerpTime<0.2){
            newDNA.lerpTime=0.2;
         }
      }
      for(int i = 0; i<newDNA.stiffness.length; i++){
         for(int i2 = 0; i2<newDNA.stiffness[i].length; i2++){
            if(Math.random()<CONFIG.MUTATE_CHANCE){
               mutated = true;
               newDNA.stiffness[i][i2] += Math.random()*CONFIG.MAXMUTATE - CONFIG.MAXMUTATE/2;
               newDNA.hDesiredLengths[i][i2] += Math.random() - Math.random()*CONFIG.MAXMUTATE - CONFIG.MAXMUTATE/2;
               newDNA.vDesiredLengths[i][i2] += Math.random() - Math.random()*CONFIG.MAXMUTATE - CONFIG.MAXMUTATE/2;
               if(newDNA.stiffness[i][i2]>CONFIG.SMAX){
                  newDNA.stiffness[i][i2]=CONFIG.SMAX;
               }else if(newDNA.stiffness[i][i2]<CONFIG.SMIN){
                  newDNA.stiffness[i][i2]=CONFIG.SMIN;
               }
               if(newDNA.hDesiredLengths[i][i2]>CONFIG.LMAX){
                  newDNA.hDesiredLengths[i][i2]=CONFIG.LMAX;
               }else if(newDNA.hDesiredLengths[i][i2]<CONFIG.LMIN){
                  newDNA.hDesiredLengths[i][i2]=CONFIG.LMIN;
               }
               if(newDNA.vDesiredLengths[i][i2]>CONFIG.LMAX){
                  newDNA.vDesiredLengths[i][i2]=CONFIG.LMAX;
               }else if(newDNA.vDesiredLengths[i][i2]<CONFIG.LMIN){
                  newDNA.vDesiredLengths[i][i2]=CONFIG.LMIN;
               }
            }
         }
      }
      if(mutated){
         char[] namechar = newDNA.name.substring(0,9).toCharArray();
         namechar[(int)(Math.random()*4)+3] = chars.charAt((int)(Math.random()*(chars.length())));
         newDNA.name = new String(namechar);
         newDNA.name += newDNA.generation;
      }
      return newDNA;
   }
}