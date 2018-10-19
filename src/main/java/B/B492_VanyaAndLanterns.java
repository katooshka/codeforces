package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B492_VanyaAndLanterns {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            int streetLength = Integer.parseInt(str.split(" ")[1]);
            String coordinatesString = br.readLine();
            List<Integer> coordinates = new ArrayList<>();
            for (String s : coordinatesString.split(" ")) {
                coordinates.add(Integer.parseInt(s));
            }
            Collections.sort(coordinates);
            double maxDistance = 0;
            for (int i = 0; i < coordinates.size() - 1; i++) {
                int currentDistance = coordinates.get(i + 1) - coordinates.get(i);
                if (currentDistance > maxDistance) {
                    maxDistance = currentDistance;
                }
            }
            int fromStartToFirst = coordinates.get(0);
            int fromLastToEnd = streetLength - coordinates.get(coordinates.size() - 1);
            int bordersMaxDistance = Math.max(fromStartToFirst, fromLastToEnd);
            System.out.println(Math.max(bordersMaxDistance, maxDistance / 2));
        }
    }
}
