package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A1003_PolycarpsPockets {
    public static void main(String[] args) throws IOException {
        int count;
        List<Integer> coins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(br.readLine());
            String[] numbers = br.readLine().split(" ");
            for (int i = 0; i < count; i++) {
                coins.add(Integer.parseInt(numbers[i]));
            }
        }
        Collections.sort(coins);
        int countDuplicates = 1;
        int result = countDuplicates;
        int previousCoin = coins.get(0);
        for (int i = 1; i < coins.size();) {
            int currentCoin = coins.get(i);
            if (currentCoin == previousCoin) {
                countDuplicates++;
                result = Math.max(result, countDuplicates);
            } else {
                previousCoin = coins.get(i);
                countDuplicates = 1;
            }
            i++;
        }
        System.out.println(result);
    }
}
