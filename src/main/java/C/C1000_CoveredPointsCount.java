package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C1000_CoveredPointsCount {

    public static void main(String[] args) throws IOException {
        int sectionsCount;
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            sectionsCount = Integer.parseInt(br.readLine());
            for (int i = 0; i < sectionsCount; i++) {
                String[] sectionArgs = br.readLine().split(" ");
                points.add(new Point(i, Long.parseLong(sectionArgs[0]), PointType.START));
                points.add(new Point(i, Long.parseLong(sectionArgs[1]), PointType.END));
            }
        }
        points.sort(Point.COMPARATOR);
        long[] result = new long[sectionsCount + 1];
        Map<Long, Integer> map = new LinkedHashMap<>();
        List<Long> coordinates = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).type.equals(PointType.START)) {
                count++;
                map.put(points.get(i).coordinate, count);
            } else {
                count--;
                map.put(points.get(i).coordinate + 1, count);
            }
        }
        coordinates.addAll(map.keySet());
        for (int i = 0; i < coordinates.size() - 1; i++) {
            int entry = map.get(coordinates.get(i));
            result[entry] = result[entry] + coordinates.get(i + 1) - coordinates.get(i);
        }
        for (int i = 1; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    static class Point {
        private static Comparator<Point> COMPARATOR =
                Comparator.<Point>comparingLong(point -> point.coordinate)
                    .thenComparingInt(point -> point.type.order)
                    .thenComparingInt(point -> point.sectionNumber);

        int sectionNumber;
        long coordinate;
        PointType type;

        Point(int sectionNumber, long coordinate, PointType type) {
            this.sectionNumber = sectionNumber;
            this.coordinate = coordinate;
            this.type = type;
        }
//
//        @Override
//        public int compareTo(Point o) {
//            int result = Long.compare(this.coordinate, o.coordinate);
//            if (result != 0) {
//                return result;
//            }
//            result = Integer.compare(this.type.order, o.type.order);
//            if (result != 0) {
//                return result;
//            }
//            return Integer.compare(this.sectionNumber, o.sectionNumber);
//        }
    }

    enum PointType {
        START(1),
        END(2);

//        private static Comparator<PointType> COMPARATOR =
//                Comparator.comparingInt(pointType -> pointType.order);

        private final int order;

        PointType(int order) {
            this.order = order;
        }


    }
}