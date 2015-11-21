package IHM_Perso;

import IHM_Perso.Util.Trigonometry;

import java.awt.*;

/**
 * La classe Poygone qui hérite de courbe pour définir un polygone
 * à partir d'un tableau de points (type APoint)
 * 
 */


public class Polygone extends Courbe{
	
	private APoint[] points;

	private APoint[] trigoPoints;

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

	@Override
	public boolean hasPoint(APoint point) {
		if (trigoPoints == null) {
			trigoPoints = Trigonometry.sort(points);
		}

		double angle = 0;


		// NE MARCHE PAS
		for (APoint currentPoint: trigoPoints) {
			angle += Trigonometry.getAngle(new APoint(currentPoint.x-point.x, currentPoint.y-point.y), false);
			System.out.println(angle);
		}
		System.out.println("-----------------------------");
		return angle == 0;
	}

	public void paintCourbe(Graphics g) {

		int[] xTab = new int[points.length];
		int[] yTab = new int[points.length];

		for(int i=0; i<points.length; i++) {
			xTab[i] = (int)points[i].x;
			yTab[i] = (int)points[i].y;
		}

		Color oldColor = g.getColor();

		g.setColor(paintColor);
		g.fillPolygon(xTab, yTab, xTab.length);
		g.setColor(oldColor);
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

}

