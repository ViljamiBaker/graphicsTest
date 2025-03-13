package Jelly;

public class SoftSquare{
   Spring[] springs = new Spring[6];//{vertical right, vertical left, horizontal up, horizontal down, (ul -> br), (ur -> bl)}
   double[] vDesiredLengths;
   double[] hDesiredLengths;
   double[] stiffness;
   int index = 0;
   int maxIndex;
   
   public SoftSquare(Spring[] springs, double[] vDesiredLengths, double[] hDesiredLengths, double[] stiffness){
      this.vDesiredLengths = vDesiredLengths;
      this.hDesiredLengths = hDesiredLengths;
      this.stiffness = stiffness;
      this.springs = springs;
      this.maxIndex = vDesiredLengths.length-1;
   }
   public void update(){
      index++;
      if(index>maxIndex){
         index = 0;
      }
      for(int i = 0; i<springs.length; i++){
         if(i==4||i==5){
            springs[i].setDLength(Math.sqrt(Math.pow(vDesiredLengths[index],2)+Math.pow(hDesiredLengths[index],2))/1.41421356237);
            springs[i].strength = stiffness[index]*1;
         }else if(i==0||i==1){
            springs[i].setDLength(vDesiredLengths[index]);
         }else if(i==2||i==3){
            springs[i].setDLength(hDesiredLengths[index]);
         }
      }
   }
   public void updateSprings(){
      for(int i = 0; i<6; i++){
         
         springs[i].setStrength(stiffness[index]); 
         springs[i].update();
      }
   }
   public void updatePoints(){
      for(int i = 0; i<6; i++){
         springs[i].updatePoints();
      }
   }
}