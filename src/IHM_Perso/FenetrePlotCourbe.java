package IHM_Perso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Kévin on 22/10/2015.
 */
public class FenetrePlotCourbe extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Courbe> courbeTab;
    public Color colorEdition = null;

    public FenetrePlotCourbe() {
        super("Graphique");

        courbeTab = new ArrayList<>();

        setLayout(null);
        setBounds(400, 300, 600, 600);

        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, 400, 400);

        setContentPane(mainPanel);
        Container c = getContentPane();

        setVisible(true);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setTitle("Coordonées: ("+e.getX()+","+e.getY()+")");
                if (colorEdition != null) {
                    ArrayList<Courbe> courbesToEdit = Courbe.courbeForPoint(courbeTab, new APoint(e.getX(), e.getY()));
                    for (Courbe c: courbesToEdit) {
                        c.setPaintColor(colorEdition);
                    }
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void addCourbe(Courbe c) {
        if (!courbeTab.contains(c))
            courbeTab.add(c);

        repaint();
    }

    public void plotCourbe(Courbe c) {
        courbeTab.remove(c);

        repaint();
    }

    public void clearPlot() {
        courbeTab.clear();

        repaint();
    }

    public void setColorEdition(Color state) {
        colorEdition = state;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.orange);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.white);

        if (courbeTab != null) {
            for(Courbe courbe : courbeTab) {
                courbe.paintCourbe(g);
            }
        }
    }
}
