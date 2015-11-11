package IHM;

import java.awt.*;

/**
 * La classe Cercle qui hérite de courbe pour définir un cercle
 * à partir d'un centre (type APoint) et d'un rayon (type entier)
 * 
 */

public class Cercle extends Courbe{
  
	private APoint centre;
	private int rayon;

	/**
	 * Le constructeur
	 * @param : un centre et un rayon
	 */ 
	public Cercle(APoint c, int r){
		centre = c;
		rayon = r;
		System.out.println("Cercle à ("+centre.x+","+centre.y+") avec rayon "+rayon+" créé.");
	}

	/**
	 * Pour calculer le périmètre d'un cercle
	 */
	public double longueur(){
		return 2.0*Math.PI*rayon;
	}

	/**
	 * Pour calculer le barycentre d'un cercle
	 */
	public APoint barycentre(){
		return centre;
	}
	
	/**
	 * Pour calculer l'aire d'un cercle
	 */
	public double aire(){
		return Math.PI*rayon*rayon;
	}

	@Override
	public void paintCourbe(Graphics g, Color color) {
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillOval((int)(centre.x-rayon), (int)(centre.y+rayon), 2*rayon, 2*rayon);
		g.setColor(oldColor);
	}

	/**
	 * La méthode toString pour renvoyer les propriétés du cercle
	 */ 
	public String toString(){
		return ("un cercle de centre ("+centre.x+","+centre.y+") et de rayon "+rayon);
	}
  
}


