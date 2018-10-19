package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A723_TheNewYearMeetingFriends {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            List<Integer> coordinates = new ArrayList<>();
            coordinates.add(Integer.parseInt(str.split(" ")[0]));
            coordinates.add(Integer.parseInt(str.split(" ")[1]));
            coordinates.add(Integer.parseInt(str.split(" ")[2]));
            Collections.sort(coordinates);
            System.out.println(coordinates.get(coordinates.size() - 1) - coordinates.get(0));
        }
    }
}
