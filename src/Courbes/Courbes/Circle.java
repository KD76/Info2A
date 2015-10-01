package Courbes.Courbes;

import Courbes.APoint;

/**
 * Created by Kévin on 26/09/2015.
 */
public class Circle extends Courbe {

    private double radius;

    public Circle(APoint center, double radius) {
        this.radius = radius;
        this.barycentre = center;
    }

    @Override
    public double longueur() {
        return 2*Math.PI*radius;
    }

    @Override
    public double aire() {
        return Math.PI*radius*radius;
    }

    public APoint barycentre() {
        return this.barycentre;
    }
}
