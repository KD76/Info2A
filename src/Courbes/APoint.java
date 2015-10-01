package Courbes;

/**
 * Created by Kévin on 26/09/2015.
 */
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

    public APoint add(double ax, double ay) {
        return new APoint(x+ax, y+ay);
    }

    public String toString() {
        return "[x=" + x + ",y=" + y + "]";
    }
}