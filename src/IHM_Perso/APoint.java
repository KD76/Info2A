package IHM_Perso;

public class APoint {
    
    public double x, y;
    
    public APoint(double ax, double ay){
        x = ax;
        y = ay;
    }
            
    public double distance( APoint otherPoint ) {
        double dx = x - otherPoint.x;
        double dy = y - otherPoint.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public String toString() {
        return  "[x=" + x + ",y=" + y + "]";
    }    
    
}

