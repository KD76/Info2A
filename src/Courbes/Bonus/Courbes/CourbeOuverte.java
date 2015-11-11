package Courbes.Bonus.Courbes;

import Courbes.APoint;

/**
 * Created by Kévin on 08/10/2015.
 */
public abstract class CourbeOuverte extends Courbe {

    protected APoint[] points;

    public double distanceExtremite() {
        return points[0].distance(points[points.length-1]);
    }

}
