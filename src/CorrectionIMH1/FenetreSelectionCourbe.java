package CorrectionIMH1; /**
 * La fenêtre principale pour sélectionner les courbes à étudier
 * et pour créer les deux autres fenêtres
 * 
 */

// Chargement des bibliothèques Swing et AWT
import CorrectionIMH1.Courbe;

import javax.swing.*;
import java.awt.*;

public class FenetreSelectionCourbe extends JFrame{
	
	public FenetreSelectionCourbe(Courbe[] tabCourbe){
		this.setTitle("IHM Courbe - Selection ");
		this.setLayout(null);
		this.setSize(400,400);
		// Pour placer la fenêtre au centre de l'écran
		//~ this.setLocationRelativeTo(null);
		this.setLocation(300,600);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(true);
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		/**
		 * Mon panneau 1
		 */
		JPanel panneauChoix = new JPanel();
		panneauChoix.setBounds(10,10,380,130);
		panneauChoix.setLayout(null);
		panneauChoix.setBackground(Color.green);
		
		JLabel affTest = new JLabel();
		affTest.setText("Sélectionnez les courbes parmi ces " + tabCourbe.length + " courbes \n");
		affTest.setBounds(25,10,350,50);
		affTest.setBackground(Color.white);
		panneauChoix.add(affTest);
		
		String[] courbeStrings = new String[tabCourbe.length+1];
		courbeStrings[0] = "Toutes les courbes";
		for (int i=1;i<=tabCourbe.length;i++)
			courbeStrings[i]=("Courbe n°"+i);
		JComboBox courbeList = new JComboBox(courbeStrings);
		courbeList.setBounds(100,80,200,20);
		panneauChoix.add(courbeList);
		
		
		
		/**
		 * Mon panneau 2
		 */
		JPanel panneauBouton = new JPanel();
		panneauBouton.setBounds(105,200,190,100);
		panneauBouton.setLayout(null);
		panneauBouton.setBackground(Color.blue);
		
		JButton monBoutonPlot = new JButton("Plot");
		monBoutonPlot.setBounds(10,10,80,80);
		monBoutonPlot.setBackground(new Color(10,144,10));
		monBoutonPlot.setForeground(Color.white);
		panneauBouton.add(monBoutonPlot);
		
		JButton monBoutonClear = new JButton("Clear");
		monBoutonClear.setBounds(100,10,80,80);
		monBoutonClear.setBackground(Color.orange);
		panneauBouton.add(monBoutonClear);
		
		
		/**
		 * Mon panneau Global
		 */
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,400,400);
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(Color.yellow);
		panneauGlobal.add(panneauChoix);
		panneauGlobal.add(panneauBouton);
		
		this.setContentPane(panneauGlobal);
		
		// Pour créer la seconde fenêtre
		FenetrePlotCourbe maFramePlotCourbe = new FenetrePlotCourbe(tabCourbe);
		
	}
}
		

		
		

		
		
		
