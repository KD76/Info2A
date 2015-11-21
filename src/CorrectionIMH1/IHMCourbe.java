package CorrectionIMH1;

/**
 * TP d'initiation au développement d'IHM sous java
 * à partir du TP courbe (classes Courbe, Cercle, Polygone et APoint)
 * 
 * Le programme principal qui permet de créer :
 * - les courbes (cercles et polygones)
 * - les fenêtres de l'IHM
 * 
 */ 

public class IHMCourbe{
	
	
	public static void main(String [] Args){
		
		// Création du tableau de courbes
		Courbe[] courbes = new Courbe[7];
		
		// Création des cercles
		Courbe c1 = new Cercle(new APoint(80,120), 50);
		Courbe c2 = new Cercle(new APoint(200,260), 30);
		Courbe c3 = new Cercle(new APoint(300,140), 50);
		Courbe c4 = new Cercle(new APoint(100,410), 60);
		Courbe c5 = new Cercle(new APoint(430,380), 90);
		
		// Création des polygones
		APoint[] p1 = new APoint[4];
		p1[0] = new APoint(400, 100);
		p1[1] = new APoint(500, 100);
		p1[2] = new APoint(400, 200);
		p1[3] = new APoint(350, 200);
		Courbe poly1 = new Polygone(p1);
		
		APoint[] p2 = new APoint[8];
		p2[0] = new APoint(200, 300); p2[1] = new APoint(250, 300);
		p2[2] = new APoint(275, 325); p2[3] = new APoint(275, 375);
		p2[4] = new APoint(250, 400); p2[5] = new APoint(200, 400);
		p2[6] = new APoint(175, 375); p2[7] = new APoint(175, 325);
		Courbe poly2 = new Polygone(p2);
		
		// Remplissage du tableau
		courbes[0] = c1;
		courbes[1] = c2;
		courbes[2] = c3;
		courbes[3] = c4;
		courbes[4] = c5;
		courbes[5] = poly1;
		courbes[6] = poly2;

		// Création de la fenêtre pour l'IHM
		FenetreSelectionCourbe maFrameSelectionCourbe = new FenetreSelectionCourbe(courbes);
		
	}
}

