package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C1003_IntenseHeat {
    public static void main(String[] args) throws IOException {
        int size;
        int minSize;
        int[] days;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstString = br.readLine().split(" ");
            size = Integer.parseInt(firstString[0]);
            minSize = Integer.parseInt(firstString[1]);
            String[] secondString = br.readLine().split(" ");
            days = new int[size];
            for (int i = 0; i < days.length; i++) {
                days[i] = Integer.parseInt(secondString[i]);
            }
        }

        double[] temperatures = new double[size];
        temperatures[0] = days[0];
        for (int i = 1; i < days.length; i++) {
            temperatures[i] = temperatures[i - 1] + days[i];
        }

        double maxIntense = 0;
        for (int i = minSize; i <= size; i++) {
            int start = 0;
            int end = start + i - 1;
            while (end < size) {
                double currentIntense = start > 0
                        ? (temperatures[end] - temperatures[start - 1]) / (end - start + 1)
                        : temperatures[end] / (end - start + 1);
                if (currentIntense > maxIntense) {
                    maxIntense = currentIntense;
                }
                start++;
                end++;
            }
        }
        System.out.println(maxIntense);
    }
}
