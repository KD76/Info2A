package IHM;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kévin on 22/10/2015.
 */
public class FenetrePlotCourbe extends JFrame {

    private JPanel mainPanel;
    private Courbe[] courbeTab;

    public FenetrePlotCourbe(Courbe[] courbes) {
        super("Graphique");

        this.courbeTab = new Courbe[courbes.length];
        System.arraycopy(courbes, 0, this.courbeTab, 0, this.courbeTab.length);

        setLayout(null);
        setBounds(400, 300, 600, 600);

        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, 400, 400);

        setContentPane(mainPanel);
        Container c = getContentPane();

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(Courbe courbe : courbeTab) {
            courbe.paintCourbe(g, Color.BLUE);
        }
    }
}
