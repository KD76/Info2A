package CorrectionIMH1; /**
 * La fenêtre pour dessiner les courbes
 * 
 */

// Chargement des bibliothèques Swing et AWT
import CorrectionIMH1.Courbe;

import javax.swing.*;
import java.awt.*;

public class FenetrePlotCourbe extends JFrame{
	private Courbe[] mesCourbes;
	
	public FenetrePlotCourbe(Courbe[] tabCourbe){
		this.setTitle("IHM Courbe - Graphique");
		this.setLayout(null);
		this.setSize(600,500);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(true);
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
		mesCourbes = tabCourbe;
	}
	
	
	public void paint(Graphics g){
		
		//Vous verrez cette phrase chaque fois que la méthode sera appelée
		System.out.println("Je suis exécutée !"); 
		g.setColor(Color.orange);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		g.setColor(Color.white);

		
		// Pour appeler les méthodes dessine des objets Courbe
		for (int i = 0;i<mesCourbes.length;i++){
			mesCourbes[i].dessine(g);
		}
	}

}
		
