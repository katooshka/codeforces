package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A500_NewYearTransportation {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] cells = br.readLine().split(" ");
            int targetCellIndex = Integer.parseInt(cells[1]);
            String[] numbersAsStrings = br.readLine().split(" ");
            List<Integer> numbers = new ArrayList<>();
            numbers.add(-1);
            for (String numbersAsString : numbersAsStrings) {
                numbers.add(Integer.parseInt(numbersAsString));
            }
            int currentCellIndex = 1;
            while (currentCellIndex < targetCellIndex) {
                currentCellIndex = numbers.get(currentCellIndex) + currentCellIndex;
            }
            System.out.println(currentCellIndex == targetCellIndex ? "YES" : "NO");
        }
    }
}

