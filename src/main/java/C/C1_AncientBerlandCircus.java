package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.*;

public class C1_AncientBerlandCircus {

    public static void main(String[] args) throws IOException {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 3; i++) {
                String[] coordinatesStrings = br.readLine().split(" ");
                points.add(new Point(Double.parseDouble(coordinatesStrings[0]), Double.parseDouble(coordinatesStrings[1])));
            }
        }
        Point centre = findCentreCoordinates(points);
        centerCoordinates(points, centre);
        double distanceToCentre = findDistanceToCenter(centre);
        List<Double> angles = matchPointsAndAngles(points);
        ResultPolygon polygon = new ResultPolygon(identifyAnglesNumber(angles), distanceToCentre);
        System.out.println(findArea(polygon));
    }

    private static Point findCentreCoordinates(List<Point> points) {
        return null;
    }

    private static double findDistanceToCenter(Point centre) {
        return 0;
    }

    private static void centerCoordinates(List<Point> points, Point centre) {
        for (Point point : points) {
            point.x = point.x - centre.x;
            point.y = point.y - centre.y;
        }
        centre.x = 0;
        centre.y = 0;
    }

    private static List<Double> matchPointsAndAngles(List<Point> centeredPoints) {
        List<Double> angles = new ArrayList<>();
        for (Point centeredPoint : centeredPoints) {
            angles.add(atan2(centeredPoint.y, centeredPoint.x));
        }
        Collections.sort(angles);
        return angles;
    }

    private static int identifyAnglesNumber(List<Double> sortedAngles) {
        for (int i = 3; i < 100; i++) {
            double polygonAngle = 360 / i;
            if (sortedAngles.get(0) % polygonAngle == 0 &&
                    sortedAngles.get(1) % polygonAngle == 0 &&
                    sortedAngles.get(2) % polygonAngle == 0) {
                return i;
            }
        }
        return -1;
    }

    private static double findArea(ResultPolygon polygon) {
        double angle = polygon.polygonAnglesSum / polygon.anglesNumber;
        double firstLeg = sin(angle) * polygon.distanceToCentre;
        double secondLeg = cos(angle) * polygon.distanceToCentre;
        return firstLeg * secondLeg;
    }

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class ResultPolygon {
        int anglesNumber;
        double distanceToCentre;
        double polygonAnglesSum;

        ResultPolygon(int anglesNumber, double distanceToCentre) {
            this.anglesNumber = anglesNumber;
            this.distanceToCentre = distanceToCentre;
            this.polygonAnglesSum = 360 / anglesNumber;
        }
    }
}
