package Courbes;

import Courbes.Courbes.Circle;
import Courbes.Courbes.Courbe;
import Courbes.Courbes.Polygone;
import Courbes.Courbes.PolygoneRegulier;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Kévin on 26/09/2015.
 */
public class Main {

    public static void main(String[] args) {
        APoint[] test = new APoint[]{new APoint(4, 0), new APoint(4, 4), new APoint(0, 0), new APoint(0, 4)};
        APoint[] test2 = new APoint[]{new APoint(0, 5), new APoint(4, 10), new APoint(4, 2), new APoint(1, 6), new APoint(0,2)};
        Polygone polyg = new Polygone(test);
        System.out.println(polyg.aire());
        System.out.println(polyg.barycentre());

        Courbe[] tabCourbes = new Courbe[]{new Polygone(test), new Circle(new APoint(20, 30), 5), new Circle(new APoint(10, 15), 2), new Polygone(new APoint[]{new APoint(-4, 20), new APoint(-1, 2), new APoint(2, 0)})};
        double totalLength = 0;
        double totalAire = 0;
        double distanceCount = 0.5*tabCourbes.length*(tabCourbes.length-1);
        double[] totalDistance = new double[(int)distanceCount];
        for (int i=0; i<tabCourbes.length; i++) {
            totalLength += tabCourbes[i].longueur();
            totalAire += tabCourbes[i].aire();
            for (int j=0; j<tabCourbes.length; j++) {
                if (i != j && (i == 0 || (j != 0 && i%j != 0))) {
                    double distance = tabCourbes[i].distance(tabCourbes[j]);
                    System.out.println("Distance entre "+i+" et "+j+": "+distance);
                    totalDistance[i+j] += tabCourbes[i].distance(tabCourbes[j]);
                }
            }

        }

        double averageDistance = 0;
        for(int i=0; i<totalDistance.length; i++) {
            averageDistance += totalDistance[i];
        }
        averageDistance /= totalDistance.length;

        double averageLength = totalLength/(double)tabCourbes.length;
        double averageAire = totalAire/(double)tabCourbes.length;

        System.out.println("Longueur totale des courbes: "+totalLength);
        System.out.println("Aire totale des courbes:" +totalAire);
        System.out.println("Longueur moyenne des courbes :"+averageLength);
        System.out.println("Aire moyenne des courbes :"+averageAire);
        System.out.println("Distance moyenne entre les courbes: "+averageDistance);

        //DEUXIEME PARTIE

        /*Polygone carre1 = new Polygone(test);
        System.out.println("---------------CARRE 1---------------");
        System.out.println("Longueur:" +carre1.longueur());
        System.out.println("Aire:"+carre1.aire());
        System.out.println("Barycentre:"+carre1.barycentre());
        PolygoneRegulier carre2 = new PolygoneRegulier(new APoint(2, 2), new APoint(0,0), 4);
        System.out.println("---------------CARRE 2---------------");
        System.out.println("Longueur:" +carre2.longueur());
        System.out.println("Aire:"+carre2.aire());
        System.out.println("Barycentre:"+carre2.barycentre());*/

        Scanner scan = new Scanner(System.in);

        System.out.println("Approximation à combien de sommets ?");
        int nbSommets = scan.nextInt();
        APoint center = new APoint(0, 0);
        int radius = 300;
        Circle cercle1 = new Circle(center, radius);
        System.out.println("---------------CERCLE PARFAIT---------------");
        System.out.println("Longueur:" +cercle1.longueur());
        System.out.println("Aire:"+cercle1.aire());
        System.out.println("Barycentre:"+cercle1.barycentre());
        PolygoneRegulier cercle2 = new PolygoneRegulier(center, new APoint(radius,0), nbSommets);
        System.out.println("---------------CERCLE APPROXIME---------------");
        System.out.println("Longueur:" +cercle2.longueur());
        System.out.println("Aire:"+cercle2.aire());
        System.out.println("Barycentre:" + cercle2.barycentre());
    }

}
