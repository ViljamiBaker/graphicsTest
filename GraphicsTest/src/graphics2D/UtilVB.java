package graphics2D;

public class UtilVB {
    public static double slopedLine(double min, double mint, double max, double maxt, double t){
        if(t<=mint){
            return min;
        }
        if(t>=maxt){
            return max;
        }
        if(maxt-mint==0){
            return (t>maxt ? max : min);
        }
        return ((max-min)/(maxt-mint))*t-((max-min)/(maxt-mint))*mint+min;
    }
    public static Vector2D interpPoints(Vector2D p1, Vector2D p2, double t){
        return new Vector2D(p2.x*t-p1.x*(t-1),p2.y*t-p1.y*(t-1));
    }
}
