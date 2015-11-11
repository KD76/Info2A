package Courbes.Bonus.Courbes;

import Courbes.APoint;

/**
 * Created by Kévin on 08/10/2015.
 */
public class LignePolygonale extends CourbeOuverte {

    public LignePolygonale(APoint[] setPoints) {
        points = new APoint[setPoints.length];
        System.arraycopy(setPoints, 0, points, 0, setPoints.length);
    }

    @Override
    public double longueur() {
        double longueur = 0;
        for(int i=0; i<points.length-1; i++) {
            longueur += points[i].distance(points[i+1]);
        }

        return longueur;
    }

    @Override
    public APoint barycentre() {
        double xG = 0;
        double yG = 0;
        for (int i=0; i<points.length; i++) {
            xG += points[i].x;
            yG += points[i].y;
        }
        xG /= (double)points.length;
        yG /= (double)points.length;

        return new APoint(xG, yG);
    }

    @Override
    public boolean convexe() {
        return false;
    }
}
