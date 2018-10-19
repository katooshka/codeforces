package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A732_BuyAShovel {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            int price = Integer.parseInt(str.split(" ")[0]);
            int value = Integer.parseInt(str.split(" ")[1]);
            int count = 1;
            while (true) {
                int cost = price * count;
                if (cost % 10 == 0 || cost % 10 == value) {
                    System.out.println(count);
                    return;
                }
                count++;
            }
        }
    }
}