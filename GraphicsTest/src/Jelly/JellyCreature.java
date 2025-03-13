package Jelly;

public class JellyCreature{
   SoftSquare[] squares = new SoftSquare[16];
   Point[] points;
   int stateTime;
   double lerpTime;
   String name;
   int generation;
   public JellyCreature(SoftSquare[] squares, Point[] points, String name, int stateTime, double lerpTime, int generation){
      this.squares = squares;
      this.points = points;
      this.name = name;
      this.stateTime = stateTime;
      this.lerpTime = lerpTime;
      this.generation = generation;
   }
   public void update(){
      for(int i = 0; i<16; i++){
         squares[i].updateSprings();
      }
      for(int i = 0; i<25; i++){
         points[i].update();
      }
   }
   public void updateSquares(){
      for(int i = 0; i<16; i++){
         squares[i].update();
      }
   }
   public double getScore(){
      double score = 0;
      for(Point p: points){
         switch(CONFIG.SCORING_CASE){
            case XFAR:
               score+=p.pos.x-400;
               break;
            case YFAR:
               score+=p.pos.y;
               break;
            case XMID:
               score+=-Math.abs(p.pos.x-400);
               break;
            default:
               score+=p.pos.x-400;
               break;
         }
      }
      return score/25;
   }
   static String chars = "QWERTYUIOPASDFGHJKLZXCVBNM!.^";
   public static String genName(){
      String name = "";
      for(int i = 0; i<7 ;i++){
         name += String.valueOf(chars.charAt((int)(Math.random()*(chars.length()-3))));
      }
      for(int i = 0; i<3 ;i++){
         name += String.valueOf(chars.charAt((int)(Math.random()*3)+26));
      }
      return name;
   }
}