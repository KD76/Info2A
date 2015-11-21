package CorrectionIMH1; /**
 * La classe Poygone qui hérite de courbe pour définir un polygone
 * à partir d'un tableau de points (type APoint)
 * 
 */

// Pour pouvoir utiliser l'objet Graphics
import java.awt.Graphics;
// Pour pouvoir choisir les couleurs
import java.awt.Color;

public class Polygone extends Courbe{
	
	private APoint[] points;

	/**
	 * Le construteur
	 * @param : le tableau de APoint représentant les sommets
	 */
	public Polygone(APoint[] p){
		points = new APoint[p.length];
		for(int i=0; i<p.length; i++)
			points[i] = new APoint(p[i].x,p[i].y);
		System.out.println("Polygone créé.");
	}


	/**
	 * Pour calculer le périmètre d'un polygone
	 */ 
	public double longueur(){
		double resultat=0;
		for(int i=1; i<points.length; i++)
			resultat += points[i-1].distance(points[i]);
		// ajouter distance entre dernier et premier point
		resultat += points[points.length-1].distance(points[0]);
		return resultat;
	}

	/**
	 * Pour calculer le barycentre d'un polygone
	 */
	public APoint barycentre(){
		APoint resultat = new APoint(0,0);
		int i;
		for(i=0; i<points.length-1; i++){
			resultat.x += (points[i].x+points[i+1].x) * (points[i].x*points[i+1].y - points[i+1].x*points[i].y);
			resultat.y += (points[i].y+points[i+1].y) * (points[i].x*points[i+1].y - points[i+1].x*points[i].y);
		}
		// "i" a la valeur points.length-1 maintenant
		resultat.x += (points[i].x+points[0].x) * (points[i].x*points[0].y - points[0].x*points[i].y);
		resultat.y += (points[i].y+points[0].y) * (points[i].x*points[0].y - points[0].x*points[i].y);
		double a = aire();
		resultat.x /= 6*a;
		resultat.y /= 6*a;
		return resultat;
	}

	/**
	 * Pour calculer l'aire d'un polygone
	 */
	public double aire(){
		double a=0;
		int i;
		for(i=0; i<points.length-1; i++)
			a += points[i].x*points[i+1].y - points[i+1].x*points[i].y;
		// "i" a la valeur points.length-1 maintenant
		a += points[i].x*points[0].y - points[0].x*points[i].y;
		return 0.5*a;
	}

	/**
	 * La méthode toString pour renvoyer les propriétés du polygone
	 */ 	
	public String toString(){
		String res;
		res = "un polygone avec "+points.length+" sommets : ";
		for(int i=0; i<points.length; i++)
			res += " (" + points[i].x + "," + points[i].y + ") ";
		return res;
	}
	
	/**
	 * La méthode dessine pour dessiner le polygone
	 * @param g: Graphics l'objet graphique où dessiner le polygone
	 */
	public void dessine(Graphics g){
		g.setColor(Color.green);
		int[] coordX = new int[points.length];
		int[] coordY = new int[points.length];
		for (int i = 0; i<points.length ; i++){
			coordX[i]=(int)points[i].x;
			coordY[i]=(int)points[i].y;
		}
		g.fillPolygon(coordX, coordY, points.length);
  }

}





