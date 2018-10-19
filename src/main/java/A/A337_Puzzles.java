package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A337_Puzzles {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            int students = Integer.parseInt(numbers[0]);
            int puzzlesNumber = Integer.parseInt(numbers[1]);
            String[] puzzlesStrings = br.readLine().split(" ");
            List<Integer> puzzles = new ArrayList<>();
            for (String puzzlesString : puzzlesStrings) {
                puzzles.add(Integer.parseInt(puzzlesString));
            }
            Collections.sort(puzzles);
            List<Integer> diffs = new ArrayList<>();
            int start = 0;
            int end = students - 1;
            while (end < puzzles.size()) {
                diffs.add(Math.abs(puzzles.get(end) - puzzles.get(start)));
                start++;
                end++;
            }
            Collections.sort(diffs);
            System.out.println(diffs.get(0));
        }
    }
}

