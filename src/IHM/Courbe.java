package IHM;

import java.awt.*;

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

	/** 
	 * Les méthodes abstraites définies chez les descendants
	 */ 
	public abstract double longueur();
	public abstract APoint barycentre();
	public abstract double aire();

	public abstract void paintCourbe(Graphics g, Color color);

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
}

