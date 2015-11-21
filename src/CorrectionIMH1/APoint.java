package CorrectionIMH1;

/**
 * La classe APoint qui permet de décrire un point dans l'espace
 * à partir de ses coordonnéees (x,y)
 * 
 */

public class APoint {
    
    // Les attributs
    public double x, y;
    
    
    // Les méthodes
    /**
     * Le constructeur :
     * @param ax: double et ay: double pour décrire le point
     */
    public APoint(double ax, double ay){
        x = ax;
        y = ay;
    }
            
    /**
     * La méthode distance pour calculer la distance euclidienne
     * entre 2 points.
     * @param otherPoint: APoint l'autre point à partir duquel on
     * cherche à calculer la distance
     * @return la distance euclidienne par rapport à l'autre point
     */        
    public double distance( APoint otherPoint ) {
        double dx = x - otherPoint.x;
        double dy = y - otherPoint.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /**
     * La méthode toString pour afficher les coordonnées du point
     * @return la chaîne de caractère sous la forme [x= ,y=]
     */
    public String toString() {
        return  "[x=" + x + ",y=" + y + "]";
    }    
    
}

