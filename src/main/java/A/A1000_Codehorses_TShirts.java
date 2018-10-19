package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class A1000_Codehorses_TShirts {
    public static void main(String[] args) throws IOException {
        int tshirtsCount;
        Map<String, Integer> tshirts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            tshirtsCount = Integer.parseInt(br.readLine());
            for (int i = 0; i < tshirtsCount; i++) {
                String size = br.readLine();
                if (!tshirts.containsKey(size)) {
                    tshirts.put(size, 0);
                }
                tshirts.put(size, tshirts.get(size) + 1);
            }
            for (int i = 0; i < tshirtsCount; i++) {
                String size = br.readLine();
                if (!tshirts.containsKey(size)) {
                    tshirts.put(size, 0);
                }
                tshirts.put(size, tshirts.get(size) - 1);
            }
        }
        int result = 0;
        for (String thirtSize : tshirts.keySet()) {
            if (tshirts.get(thirtSize) > 0) {
                result = result + tshirts.get(thirtSize);
            }
        }
        System.out.println(result);
    }
}
