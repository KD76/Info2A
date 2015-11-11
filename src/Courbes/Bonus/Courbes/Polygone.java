package Courbes.Bonus.Courbes;

import Courbes.APoint;
import Courbes.Util.Trigonometry;

/**
 * Created by Kévin on 26/09/2015.
 */
public class Polygone extends CourbeFermee {

    private APoint[] points;

    /**
     * Construit un polynôme en triant les points par ordre trigonométrique.
     * @param argPoints
     */
    public Polygone(APoint[] argPoints) {
        points = new APoint[argPoints.length];
        System.out.print('{');
        for(int i=0; i<argPoints.length; i++) {
            System.out.print(argPoints[i]);
            System.out.print(',');
        }
        System.out.println('}');
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

        APoint[] orderedTab = Trigonometry.sort(points);

        for (int i=0; i<orderedTab.length; i++) {
            APoint otherPt = (i+1 == orderedTab.length) ? orderedTab[0] : orderedTab[i+1];
            aire += orderedTab[i].x*otherPt.y - otherPt.x*orderedTab[i].y;
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

    @Override
    public boolean convexe() {
        for(int i=0; i<points.length; i++) {
            APoint oldPt = (i-1 < 0) ? points[points.length-1]:points[i-1];
            APoint nextPt = (i+1 == points.length) ? points[0]:points[i+1];

            double oppLength = oldPt.distance(nextPt);
            double oldLength = oldPt.distance(points[i]);
            double nextLength = nextPt.distance(points[i]);

            double rapport = oldLength*oldLength+nextLength*nextLength-oppLength*oppLength;
            rapport /= 2*oldLength*nextLength;

            double angle = Math.acos(rapport);

            System.out.println("Angle entre "+oldPt+" et "+nextPt+": "+angle);

            /*if (angle < 0)
                return false;*/
        }
        return true;
    }
}
