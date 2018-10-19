package A;

import java.io.IOException;

import java.io.*;

public class A546_SoldierAndBananas {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            int bananaCost = Integer.parseInt(numbers[0]);
            int money = Integer.parseInt(numbers[1]);
            int bananasToBuy = Integer.parseInt(numbers[2]);

            int totalCost = bananaCost * (1 + bananasToBuy) * bananasToBuy / 2;
            int result = totalCost > money ? totalCost - money : 0;
            System.out.println(result);
        }
    }
}