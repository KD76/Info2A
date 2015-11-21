package IHM_Perso;

import java.awt.*;
import java.util.ArrayList;

/**
 * La classe Courbe ancêtre de Polygone et Cercle
 * 
 */


public abstract class Courbe{
  
	/**
	 * Le constructeur
	 */
	public Courbe(){
		System.out.println("Courbe créée.");
	}

    protected Color paintColor = Color.BLUE;

	/** 
	 * Les méthodes abstraites définies chez les descendants
	 */ 
	public abstract double longueur();
	public abstract APoint barycentre();
	public abstract double aire();

	public abstract boolean hasPoint(APoint point);

	public abstract void paintCourbe(Graphics g);

    public void setPaintColor(Color color) {
        if (color != null)
            paintColor = color;
    }

	/**
	 * La méthode distance pour calculer la distance euclidienne
	 * par rapport à une autre courbe
	 * @param : l'autre courbe
	 * @return : la distance euclidienne
	 */
	public double distance(Courbe courbe){
		double resultat;
		APoint b1 = barycentre();
		APoint b2 = courbe.barycentre();
		resultat = b1.distance(b2);
		return resultat;
	}

	public static ArrayList<Courbe> courbeForPoint(ArrayList<Courbe> courbes, APoint point) {
		ArrayList<Courbe> foundCourbes = new ArrayList<>();
		for (Courbe c: courbes) {
			if (!foundCourbes.contains(c) && c.hasPoint(point))
				foundCourbes.add(c);
		}

		return foundCourbes;
	}
}

