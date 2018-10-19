package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A4_Watermelon {
    public static void main(String[] args) throws IOException {
        int weight;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            weight = Integer.parseInt(br.readLine());
        }
        int secondPart = weight - 2;
        if (secondPart <= 0 || secondPart % 2 != 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}
