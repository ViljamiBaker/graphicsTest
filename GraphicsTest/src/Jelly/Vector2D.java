package Jelly;

public class Vector2D {
   public double x;
   public double y;
   public Vector2D(double x, double y){
      this.x = x;
      this.y = y;
   }
   
   public VectorMD convert(){
      return new VectorMD(magnitude(),dir());
   }
   
   public Vector2D clone(){
      return new Vector2D(this.x, this.y);
   }
   public Vector2D add(Vector2D other){
      return new Vector2D(this.x + other.x, this.y + other.y);
   }
   public Vector2D add(Double other){
      return new Vector2D(this.x + other, this.y + other);
   }
   public double magnitude(){
      return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2));
   }
   public Vector2D n(){
      return new Vector2D(this.x * -1, this.y * -1);
   }
   public Vector2D multiply(double m){
      return new Vector2D(this.x*m, this.y*m);
   }
   public Vector2D div(double m){
      return new Vector2D(this.x/m, this.y/m);
   }
   public double dir(){
      return Math.atan2(this.x,this.y);
   }
   public double dot(Vector2D other){
      return (this.x * other.x + this.y * other.y);
   }
   public Vector2D normal(){
      return this.div(this.magnitude());
   }
   public double angleDiff(Vector2D other){
      return Math.acos((this.dot(other))/(this.magnitude()*other.magnitude()));
   }
}