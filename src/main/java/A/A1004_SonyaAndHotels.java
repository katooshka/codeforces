package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A1004_SonyaAndHotels {
    public static void main(String[] args) throws IOException {
        int hotelsNumber;
        int distance;
        int[] hotels;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            hotelsNumber = Integer.parseInt(firstArgs[0]);
            distance = Integer.parseInt(firstArgs[1]);
            hotels = new int[hotelsNumber];
            String[] secondArgs = br.readLine().split(" ");
            for (int i = 0; i < secondArgs.length; i++) {
                hotels[i] = Integer.parseInt(secondArgs[i]);
            }
        }
        int count = 0;
        for (int i = 0; i < hotelsNumber - 1; i++) {
            if (Math.abs(hotels[i + 1] - hotels[i]) > distance * 2) {
                count += 2;
            } else if (Math.abs(hotels[i + 1] - hotels[i]) == distance * 2) {
                count++;
            }
        }
        System.out.println(count + 2);
    }
}
