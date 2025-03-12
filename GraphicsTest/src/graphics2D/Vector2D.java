package graphics2D;

public class Vector2D {
   public double x;
   public double y;
   public Vector2D(double x, double y){
      this.x = x;
      this.y = y;
   }
   public Vector2D(double a){
      this.x = a;
      this.y = a;
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
   public Vector2D addX(Double other){
      return new Vector2D(this.x + other, this.y);
   }
   public Vector2D addY(Double other){
      return new Vector2D(this.x, this.y + other);
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
      if(this.magnitude() == 0.0) return new Vector2D(0,0);
      return this.div(this.magnitude());
   }
   public double angleDiff(Vector2D other){
      return Math.acos((this.dot(other))/(this.magnitude()*other.magnitude()));
   }
   public Vector2D rotateBy(VectorMD other){
      return new Vector2D(this.x*Math.sin(other.D),this.y*Math.cos(other.D));
   }
   @Override
   public String toString(){
      return "X: " + x + " Y: " + y;
   }
   public boolean equals(Vector2D other){
      return other.x==this.x&&other.y==this.y;
   }
}