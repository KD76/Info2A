package Courbes.Util;

import Courbes.APoint;

/**
 * Created by Kévin on 07/10/2015.
 */
public class Trigonometry {

    public static void sort(APoint[] points) {
        for (int i=0; i<points.length; i++) {
            int minAnglePos = getMinAnglePoint(points, i);
            if (minAnglePos != i) {
                switchLines(points, minAnglePos, i);
            }
        }
    }

    private static void switchLines(APoint[] points, int oldPos, int newPos) {
        APoint tmpData = points[oldPos];
        points[oldPos] = points[newPos];
        points[newPos] = tmpData;
    }

    private static int getMinAnglePoint(APoint[] points, int start) {
        if (start < 0 || start >= points.length) {
            return -1;
        }
        double minAngle = 2*Math.PI;
        int minPos = 0;
        for (int i=start; i<points.length; i++) {
            double angle = getAngle(points[i], true);
            if (minAngle >= angle) {
                minAngle = angle;
                minPos = i;
            }
        }
        return minPos;
    }

    public static double getAngle(APoint point, boolean positive) {
        double angle = Math.atan2(point.y, point.x);
        return (positive) ? getPositiveAngle(angle):angle;
    }

    private static double getPositiveAngle(double angle) {
        if (angle < 0)
            angle = Math.abs(angle) + Math.PI;

        return angle;
    }

}
