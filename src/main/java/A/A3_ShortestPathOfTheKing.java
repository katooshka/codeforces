package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A3_ShortestPathOfTheKing {
    public static void main(String[] args) throws IOException {
        String startCoordinate;
        String endCoordinate;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            startCoordinate = br.readLine();
            endCoordinate = br.readLine();
        }

        Point start = new Point(startCoordinate.charAt(0), Character.getNumericValue(startCoordinate.charAt(1)));
        Point end = new Point(endCoordinate.charAt(0), Character.getNumericValue(endCoordinate.charAt(1)));

        if (start.x == end.x) {
            System.out.println(Math.abs(start.y - end.y));
            List<String> path = findVerticalPath(start, end);
            for (String step : path) {
                System.out.println(step);
            }
        } else if (start.y == end.y) {
            System.out.println(Math.abs(start.x - end.x));
            List<String> path = findHorizontalPath(start, end);
            for (String step : path) {
                System.out.println(step);
            }
        } else {
            System.out.println(Math.max(Math.abs(start.x - end.x), Math.abs(start.y - end.y)));
            List<String> path = findMixedPath(start, end);
            for (String step : path) {
                System.out.println(step);
            }
        }
    }

    private static List<String> findVerticalPath(Point start, Point end) {
        List<String> result = new ArrayList<>();
        String direction;
        if (start.y >= end.y) {
            direction = "D";
        } else {
            direction = "U";
        }
        for (int i = 0; i < Math.abs(end.y - start.y); i++) {
            result.add(direction);
        }
        return result;
    }

    private static List<String> findHorizontalPath(Point start, Point end) {
        List<String> result = new ArrayList<>();
        String direction;
        if (start.x >= end.x) {
            direction = "L";
        } else {
            direction = "R";
        }
        for (int i = 0; i < Math.abs(end.x - start.x); i++) {
            result.add(direction);
        }
        return result;
    }

    private static List<String> findMixedPath(Point start, Point end) {
        List<String> result = new ArrayList<>();
        List<String> horizontalPath = findHorizontalPath(start, end);
        List<String> verticalPath = findVerticalPath(start, end);
        String horizontalDirection = horizontalPath.get(0);
        String verticalDirection = verticalPath.get(0);
        int horizontalLength = horizontalPath.size();
        int verticalLength = verticalPath.size();
        if (horizontalLength >= verticalLength) {
            for (int i = 0; i < horizontalLength - verticalLength; i++) {
                result.add(horizontalDirection);
            }
            for (int i = 0; i < verticalLength; i++) {
                result.add(horizontalDirection + verticalDirection);
            }
        } else {
            for (int i = 0; i < verticalLength - horizontalLength; i++) {
                result.add(verticalDirection);
            }
            for (int i = 0; i < horizontalLength; i++) {
                result.add(horizontalDirection + verticalDirection);
            }
        }
        return result;
    }

    static class Point {
        int x;
        int y;

        public Point(char x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
