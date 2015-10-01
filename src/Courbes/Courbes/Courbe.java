package Courbes.Courbes;

import Courbes.APoint;

/**
 * Created by Kévin on 26/09/2015.
 */
public abstract class Courbe {

    protected APoint barycentre;

    public abstract double longueur();

    public abstract double aire();

    public abstract APoint barycentre();

    public double distance(Courbe autreCourbe) {
        return barycentre().distance(autreCourbe.barycentre());
    }

    protected Courbe() {
        String className = getClass().getName();
        String[] explode = className.split("\\.");
        System.out.println("Création d'un "+explode[explode.length-1]);
    }

}
