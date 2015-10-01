package Courbes.Courbes;

import Courbes.APoint;

/**
 * Created by Kévin on 26/09/2015.
 */
public class Polygone extends Courbe {

    private APoint[] points;

    /**
     * ATTENTION, IL MANQUE LE TRI DES POINTS EN ORDRE TRIGONOMETRIQUE
     * @param argPoints
     */
    public Polygone(APoint[] argPoints) {
        points = new APoint[argPoints.length];
        System.arraycopy(argPoints, 0, points, 0, argPoints.length);
    }

    protected Polygone() {

    }

    @Override
    public double longueur() {
        double longueur = 0;
        for(int i=0; i<points.length; i++) {
            APoint otherPt = (i + 1 == points.length) ? points[0] : points[i+1];
            longueur += points[i].distance(otherPt);
        }

        return longueur;
    }

    @Override
    public double aire() {
        double aire = 0;

        for (int i=0; i<points.length; i++) {
            APoint otherPt = (i+1 == points.length) ? points[0] : points[i+1];
            aire += points[i].x*otherPt.y - otherPt.x*points[i].y;
        }

        aire *= 0.5;
        return aire;
    }

    @Override
    public APoint barycentre() {
        if (barycentre == null) {
            double xG = 0;
            double yG = 0;

            for(int i=0; i<points.length;i++) {
                APoint nextPt = (i+1 == points.length) ? points[0]:points[i+1];
                double commonProduct = points[i].x*nextPt.y-points[i].y*nextPt.x;
                xG += (points[i].x+nextPt.x)*commonProduct;
                yG += (points[i].y+nextPt.y)*commonProduct;
            }

            double aire = aire();
            xG *= 1/(6*aire);
            yG *= 1/(6*aire);

            barycentre = new APoint(xG, yG);
        }

        return barycentre;
    }
}
