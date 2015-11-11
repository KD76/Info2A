package Courbes.Bonus;

import Courbes.APoint;
import Courbes.Bonus.Courbes.LignePolygonale;
import Courbes.Bonus.Courbes.Polygone;

/**
 * Created by Kévin on 08/10/2015.
 */
public class Main {

    public static void main(String[] args) {
        APoint[] listPoints = new APoint[]{new APoint(0,0), new APoint(0, 4), new APoint(4, 4), new APoint(4, 0), new APoint(2,2)};

        Polygone test = new Polygone(listPoints);

        System.out.println("Convexe ? "+test.convexe());

        /*System.out.println("Distance euclidienne de la courbe: "+test.distanceExtremite());
        System.out.println("Barycentre :" + test.barycentre());*/
    }

}
