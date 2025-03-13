package Jelly;

public class Point{
   static Vector2D gravity = new Vector2D(0,CONFIG.GRAVP);
   static double floorHeight = CONFIG.FLOOR_HEIGHT;
   Vector2D pos;
   Vector2D vel;
   public Point(Vector2D pos){
      this.pos = pos;
      this.vel = new Vector2D(0,0);
   }
   public void update(){
      vel = vel.add(gravity);
      vel = vel.multiply(CONFIG.DRAG);
      pos = pos.add(vel);
      if(pos.y<floorHeight){
         vel.y=0;
         pos.y=floorHeight;
         vel.x*=CONFIG.GROUND_DRAG;
      }
   }
}