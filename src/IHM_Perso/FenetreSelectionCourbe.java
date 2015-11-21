package IHM_Perso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Kévin on 22/10/2015.
 */
public class FenetreSelectionCourbe extends JFrame {

    private JLabel headLabel;
    private JComboBox headList;

    private JLabel colorLabel;
    private JComboBox colorList;
    private JButton colorEnabler;

    private JButton plotButton;
    private JButton clearButton;
    private JPanel[] panels;

    private Courbe[] courbes;
    private String[] selection = new String[] {"Toutes les courbes", "La courbe n°1", "La courbe n°2", "La courbe n°3", "La courbe n°4", "La courbe n°5", "La courbe n°6", "La courbe n°7"};
    private Color[] colors = new Color[] {null, Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.CYAN, Color.MAGENTA};

    private FenetrePlotCourbe maFramePlotCourbe;

    public FenetreSelectionCourbe(Courbe[] courbes) {
        super("IHM Courbe - Sélection");
        setBounds(300, 200, 400, 400);
        setBackground(Color.ORANGE);
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

        colorLabel = new JLabel("Modifier la couleur des courbes:");
        colorLabel.setHorizontalAlignment(JLabel.CENTER);
        panels[1].add(colorLabel);
        colorLabel.setBounds(50, 75, 300, 25);

        colorList = new JComboBox(colors);
        colorList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    maFramePlotCourbe.colorEdition = (Color)e.getItem();
                }
            }
        });
        panels[1].add(colorList);
        colorList.setBounds(25, colorLabel.getY()+25, 350, 25);

        /*colorEnabler = new JButton("Activer la modification de couleur");
        colorEnabler.setHorizontalAlignment(JLabel.CENTER);
        colorEnabler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int colorIndex = colorList.getSelectedIndex();
                if (maFramePlotCourbe.colorEdition == null) {
                    maFramePlotCourbe.colorEdition = colors[colorIndex];
                    ((JButton)e.getSource()).setText("Désactiver la modification de couleur");
                } else {
                    maFramePlotCourbe.colorEdition = null;
                    ((JButton)e.getSource()).setText("Activer la modification de couleur");
                }
            }
        });
        panels[1].add(colorEnabler);
        colorEnabler.setBounds(25, colorList.getY()+colorList.getHeight()+25, 350, 25);*/


        /* Ajout des composants dans le JPanel du bas */

        plotButton = new JButton("Plot");
        panels[2].add(plotButton);
        plotButton.setBounds(75, 100, 100, 50);
        plotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = headList.getSelectedIndex();
                switch (index) {
                    case 0:
                        for (Courbe c: courbes) {
                            maFramePlotCourbe.addCourbe(c);
                        }
                        break;
                    default:
                        if (index <= courbes.length)
                            maFramePlotCourbe.addCourbe(courbes[index-1]);
                        break;

                }
            }
        });

        clearButton = new JButton("Clear");
        panels[2].add(clearButton);
        clearButton.setBounds(200, 100, 100, 50);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = headList.getSelectedIndex();

                switch (index) {
                    case 0:
                        maFramePlotCourbe.clearPlot();
                        break;
                    default:
                        if (index <= courbes.length)
                            maFramePlotCourbe.plotCourbe(courbes[index-1]);
                        break;

                }
            }
        });

        /* Ajout des panels dans le container */

        c.add(panels[1]);
        panels[1].setBounds(0, 0, 400, 200);

        c.add(panels[2]);
        panels[2].setBounds(0, 200, 400, 200);

        setVisible(true);

        maFramePlotCourbe = new FenetrePlotCourbe();

    }
}
