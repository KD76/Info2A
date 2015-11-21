package Asteroids;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Created by Kévin on 19/11/2015.
 */
public class Jeu extends JFrame {

    private Image imageVaisseau;
    private Rectangle ecran;
    private Image wallpaper;
    private Image explosionVaisseau;
    private Image explosionMeteorite;

    private BufferedImage mainBuffer;

    private Timer gameTimer;

    private int nbMeteoritesMax = 20;
    Objet[] meteorites;
    Objet[] missiles;
    Objet vaisseau;

    private int tempsExplosion_ms = 5;
    private int nbVies = 3;

    private int nbMissilesMax = 10;
    private int vitesseMissile = 40;
    private int nbMissiles = 0;
    private int score = 0;

    boolean toucheGauche;
    boolean toucheDroit;
    boolean toucheEspace;

    public Jeu() {
        setBounds(0, 0, 1000, 800);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ecran = new Rectangle(0, 0, getSize().width, getSize().height);

        mainBuffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);

        meteorites = buildMeteorites(nbMeteoritesMax);

        vaisseau = new Objet("navire.png", ecran.width/2, ecran.height/2, 0, 0);

        gameTimer = new Timer(100, new TimerListener());
        gameTimer.start();

        Toolkit T = Toolkit.getDefaultToolkit();
        explosionVaisseau = T.getImage("explosion.gif");
        explosionMeteorite = T.getImage("explosion2.gif");
        wallpaper = T.getImage("space.png");
        imageVaisseau = T.getImage("navire.png");

        //--- Initialisation Missiles -- \\
        missiles = new Objet[nbMissilesMax];
        nbMissiles = 0;

        this.addKeyListener(new GameKeyListener());

        setVisible(true);
    }

    public Objet[] buildMeteorites(int capacity) {
        Objet[] builder = new Objet[capacity];
        for(int i=0; i<capacity; i++) {
            int x = (int)((double)ecran.width*Math.random());
            int y = (int)((double)ecran.height*Math.random());
            float direction = (float)(2*Math.PI*Math.random());
            float vitesse = (float)(1 + (double)21*Math.random());

            int meteor = i%12;

            builder[i] = new Objet("Meteor"+meteor+".png", x, y, direction, vitesse);
        }
        return builder;
    }

    private void GestionVaisseau() {
        if (vaisseau.temps_explosion == 1) {
            vaisseau.actif = true;
        }
        if (vaisseau.temps_explosion > 0) {
            vaisseau.temps_explosion--;
        }
        if (toucheDroit) {
            vaisseau.direction += Math.PI/16;
        }
        if (toucheGauche) {
            vaisseau.direction -= Math.PI/16;
        }

        if (vaisseau.direction > 2*Math.PI) {
            vaisseau.direction -= 2*Math.PI;
        }
        if (vaisseau.direction < 0) {
            vaisseau.direction += 2*Math.PI;
        }
    }

    private void GestionMissiles() {
        if (toucheEspace && nbMissiles <= nbMissilesMax) {
            missiles[nbMissiles] = new Objet("bullet.gif", vaisseau.x+vaisseau.l/2, vaisseau.y+vaisseau.h/2, vaisseau.direction, vitesseMissile);
        }

        for (Objet missile: missiles) {
            if (missile != null)
                missile.move(ecran);
        }
    }

    private void GestionMeteorites() {
        for(int k=0; k<meteorites.length; k++) {
            if (meteorites[k].temps_explosion > 0) {
                meteorites[k].temps_explosion--;
            }
            meteorites[k].move(ecran);
            if (vaisseau.actif && meteorites[k].actif && vaisseau.Collision(meteorites[k])) {
                meteorites[k].temps_explosion = tempsExplosion_ms;
                meteorites[k].vitesse = 0;
                meteorites[k].direction = 0;
                vaisseau.temps_explosion = tempsExplosion_ms;
                vaisseau.actif = false;
                //nbVies--;
                /*if (nbVies <= 0)
                    gameTimer.stop();*/
            }
            if (!meteorites[k].actif) {
                meteorites[k].x = meteorites[k].x%1000;
                meteorites[k].y = meteorites[k].y%800;
                float direction = (float)(2*Math.PI*Math.random());
                float vitesse = (float)(1 + (double)21*Math.random());
                meteorites[k].vitesse = vitesse;
                meteorites[k].direction = direction;
                meteorites[k].actif = true;
            }
            if (meteorites[k].temps_explosion <= 1) {
                meteorites[k].actif = true;
            }
            if (meteorites[k].temps_explosion == 1) {
                meteorites[k].temps_explosion = 0;
                int randomX = (int)((double)2*Math.random());
                int randomY = (int)((double)2*Math.random());
                meteorites[k].x = (randomX >= 1) ? 0 : ecran.width;
                meteorites[k].y = (randomY >= 1) ? 0: ecran.height;
                meteorites[k].actif = false;
            }
        }
    }

    private void paintObjetTab(Objet[] objTab, Graphics g, Image explosion) {
        for (Objet anObjTab : objTab) {
            if (anObjTab != null) {
                if (anObjTab.temps_explosion == 0) {
                    g.drawImage(anObjTab.image, anObjTab.x, anObjTab.y, this);
                } else {
                    int x = anObjTab.x + anObjTab.l / 2 - explosion.getWidth(this) / 2;
                    int y = anObjTab.y + anObjTab.h / 2 - explosion.getHeight(this) / 2;
                    g.drawImage(explosion, x, y, this);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);

        Graphics bufferGraphics = mainBuffer.getGraphics();

        bufferGraphics.drawImage(wallpaper, 0, 0, this);
        //bufferGraphics.drawImage(explosionMeteorite, 400, 150, this);
        paintObjetTab(meteorites, bufferGraphics, explosionMeteorite);
        paintObjetTab(missiles, bufferGraphics, explosionVaisseau);

        if (vaisseau.temps_explosion == 0) {
            AffineTransform rotation = new AffineTransform();
            rotation.rotate(vaisseau.direction, vaisseau.l/2, vaisseau.h/2);
            AffineTransformOp operation = new AffineTransformOp(rotation, AffineTransformOp.TYPE_BILINEAR);
            BufferedImage vaisseauWithRotation = operation.filter(vaisseau.image, null);

            bufferGraphics.drawImage(vaisseauWithRotation, vaisseau.x, vaisseau.y, this);
        } else {
            int x = vaisseau.x+vaisseau.l/2-explosionVaisseau.getWidth(this)/2;
            int y =vaisseau.y+vaisseau.h/2-explosionVaisseau.getHeight(this)/2;
            bufferGraphics.drawImage(explosionVaisseau, x, y, this);
        }

        g.drawImage(mainBuffer, 0, 0, this);
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
    }

    public class TimerListener implements ActionListener {
        private int count=0;

        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            double value = (double)count/10;
            setTitle("Temps écolué: "+value+"s");
            GestionMissiles();
            GestionMeteorites();
            GestionVaisseau();
            repaint();
        }
    }

    public class GameKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                toucheGauche = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                toucheDroit = true;
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
                toucheEspace = true;
            if (gameTimer.isRunning() && e.getKeyCode() == KeyEvent.VK_ENTER)
                gameTimer.stop();
            if (gameTimer.isRunning() && e.getKeyCode() == KeyEvent.VK_ESCAPE)
                System.exit(0);
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                toucheGauche = false;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                toucheDroit = false;
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
                toucheEspace = false;
            if (!gameTimer.isRunning() && e.getKeyCode() == KeyEvent.VK_ENTER)
                gameTimer.start();
        }
    }
}
