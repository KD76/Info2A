package Courbes.Bonus.Courbes;

import Courbes.APoint;

/**
 * Created by Kévin on 01/10/2015.
 */
public class PolygoneRegulier extends Polygone {

    private int nbCotes;
    private double coteLongueur;

    public PolygoneRegulier(APoint centre, APoint un_point, int n_cotes) {
        nbCotes = n_cotes;
        barycentre = centre;
        APoint[] points = new APoint[n_cotes];
        double angleRegulier = 2*Math.PI/n_cotes;
        double angleDepart = Math.atan2(un_point.y-centre.y, un_point.x-centre.x);
        double distanceCentre = centre.distance(un_point);
        for(int i=0; i<points.length; i++) {
            points[i] = new APoint(distanceCentre*Math.cos(angleRegulier*i+angleDepart), distanceCentre*Math.sin(angleRegulier*i+angleDepart));
        }
        coteLongueur = points[0].distance(points[1]);
    }

    @Override
    public double longueur() {
        return coteLongueur*nbCotes;
    }

    @Override
    public double aire() {
        return nbCotes*coteLongueur*coteLongueur/(4*Math.tan(Math.PI/(double)nbCotes));
    }

    @Override
    public boolean convexe() {
        return true;
    }
}
