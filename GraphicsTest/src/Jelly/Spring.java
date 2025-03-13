package Jelly;

public class Spring{
   Point p1;
   Point p2;
   private double desiredLength;
   private double desiredLengthMultOld = 1;
   private double desiredLengthMultNew = 1;
   private double[] desiredLengthMultNews = new double[] {0.0,0.0};
   private double desiredLengthMult = 1;
   private double lerp = 0;
   private double lerpTime;
   private int stateTime;
   double strength;
   public Spring(Point p1, Point p2, double strength, double lerpTime, int stateTime){
      this.p1 = p1;
      this.p2 = p2;
      this.desiredLength = p1.pos.add(p2.pos.n()).magnitude();
      this.strength = strength;
      this.lerpTime = lerpTime;
      this.stateTime = stateTime;
   }
   public void update(){
      if(desiredLengthMultNews[0] != 0.0){
         if(desiredLengthMultNews[1] != 0.0){
            desiredLengthMultNew = (desiredLengthMultNews[0]+desiredLengthMultNews[1])/2.0;   
         }else{
            desiredLengthMultNew = desiredLengthMultNews[0];
         }
         desiredLengthMultNews = new double[] {0.0,0.0};
      }
      lerp+=1.0/(stateTime*lerpTime);//((double)CONFIG.MAX_STATE_TIME*CONFIG.LERP_TIME);
      if(lerp>1){
         lerp = 1;
      }
      desiredLengthMult = desiredLengthMultOld*(1.0-lerp)+desiredLengthMultNew*(lerp);
      VectorMD F1 = new VectorMD(Math.min(-strength*(desiredLength*desiredLengthMult-p1.pos.add(p2.pos.n()).magnitude()),1),p2.pos.add(p1.pos.n()).convert().D);
      VectorMD F2 = new VectorMD(Math.min(-strength*(desiredLength*desiredLengthMult-p1.pos.add(p2.pos.n()).magnitude()),1),p1.pos.add(p2.pos.n()).convert().D);
      p1.vel = p1.vel.add(F1.mult(1.0/2).convert());
      p2.vel = p2.vel.add(F2.mult(1.0/2).convert());
   }
   public void updatePoints(){
      p1.update();
      p2.update();
   }
   public void setDLength(double desiredLength){
      lerp = 0;
      this.desiredLengthMultOld = desiredLengthMult;
      if(desiredLengthMultNews[0] == 0.0){
         desiredLengthMultNews[0] = desiredLength;
      }else{
         desiredLengthMultNews[1] = desiredLength;
      }
   }
   public void setStrength(double strength){
      this.strength = strength;
   }
   public double getDesire(){
      return Math.max(Math.min(Math.abs(desiredLength*desiredLengthMult-p1.pos.add(p2.pos.n()).magnitude())/(desiredLength*desiredLengthMult),1),0);
   }
   public double getLmult(){
      return Math.max(Math.min(desiredLengthMult-0.5,1),0);
   }
   public double getLerp(){
      return lerp;
   }
   public double getLeng(){
      return desiredLengthMult;
   }
   public double getStr(){
      return strength;
   }
}