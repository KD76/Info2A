package CorrectionIMH1;
/**
 * La classe Cercle qui hérite de courbe pour définir un cercle
 * à partir d'un centre (type APoint) et d'un rayon (type entier)
 * 
 */

// Pour pouvoir utiliser l'objet Graphics
import java.awt.Graphics;
// Pour pouvoir choisir les couleurs
import java.awt.Color;



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
  
	/**
	 * La méthode toString pour renvoyer les propriétés du cercle
	 */ 
	public String toString(){
		return ("un cercle de centre ("+centre.x+","+centre.y+") et de rayon "+rayon);
	}
	
	/**
	 * La méthode dessine pour dessiner le cercle
	 * @param g: Graphics l'objet graphique où dessiner le cercle
	 */
	public void dessine(Graphics g){
		System.out.println(this);
		g.setColor(Color.blue);
		g.fillOval((int)(centre.x)-rayon,(int)(centre.y)-rayon,2*rayon,2*rayon);
	}
  
  
}



