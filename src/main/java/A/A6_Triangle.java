package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A6_Triangle {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] strings = br.readLine().split(" ");
            for (String string : strings) {
                numbers.add(Integer.parseInt(string));
            }
        }
        Collections.sort(numbers);
        String result = "IMPOSSIBLE";
        int firstTriangle = numbers.get(0) + numbers.get(1);
        int secondTriangle = numbers.get(1) + numbers.get(2);
        if (firstTriangle == numbers.get(2) || secondTriangle == numbers.get(3)) {
            result = "SEGMENT";
        }
        if (firstTriangle > numbers.get(2) || secondTriangle > numbers.get(3)) {
            result = "TRIANGLE";
        }
        System.out.println(result);
    }
}
