package IHM;

import Courbes.Courbes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kévin on 22/10/2015.
 */
public class FenetreSelectionCourbe extends JFrame {

    private JLabel headLabel;
    private JComboBox headList;
    private JButton plotButton;
    private JButton clearButton;
    private JPanel[] panels;

    private Courbe[] courbes;
    private String[] selection = new String[] {"Toutes les courbes", "La courbe n°1", "La courbe n°2", "La courbe n°3", "La courbe n°4", "La courbe n°5", "La courbe n°6", "La courbe n°7"};

    public FenetreSelectionCourbe(Courbe[] courbes) {
        super("IHM Courbe - Sélection");
        setBounds(300, 200, 400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setLayout(null);

        panels = new JPanel[]{new JPanel(null), new JPanel(null), new JPanel(null)};

        this.courbes = new Courbe[courbes.length];
        System.arraycopy(courbes, 0, this.courbes, 0, this.courbes.length);

        /* On définit le panel n°1 comme panel principal, donc comme container */
        setContentPane(panels[0]);
        Container c = getContentPane();

        /* Ajout des composants dans le JPanel du haut */

        headLabel = new JLabel("Sélectionnez les courbes:");
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        panels[1].add(headLabel);
        headLabel.setBounds(50, 0, 300, 25);

        headList = new JComboBox(selection);
        panels[1].add(headList);
        headList.setBounds(25, 25, 350, 25);

        /* Ajout des composants dans le JPanel du bas */

        plotButton = new JButton("Plot");
        panels[2].add(plotButton);
        plotButton.setBounds(75, 100, 100, 50);
        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Courbe[] selectedCourbes;
                int index = headList.getSelectedIndex();
                switch (index) {
                    case 0:
                        selectedCourbes = courbes;
                        break;
                    default:
                        selectedCourbes = new Courbe[]{courbes[index-1]};
                        break;

                }
                FenetrePlotCourbe maFramePlotCourbe = new FenetrePlotCourbe(selectedCourbes);
            }
        });

        clearButton = new JButton("Clear");
        panels[2].add(clearButton);
        clearButton.setBounds(200, 100, 100, 50);

        /* Ajout des panels dans le container */

        c.add(panels[1]);
        panels[1].setBounds(0, 0, 400, 200);

        c.add(panels[2]);
        panels[2].setBounds(0, 200, 400, 200);

        setVisible(true);

    }
}
