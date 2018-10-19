package A;

import java.io.IOException;

import java.io.*;

public class A1_TheatreSquare {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            int m = Integer.parseInt(str.split(" ")[0]);
            int n = Integer.parseInt(str.split(" ")[1]);
            int a = Integer.parseInt(str.split(" ")[2]);
            int result = 0;
            if (m % a == 0) {
                if (n % a == 0) {
                    result += (m / a) * (n / a);
                } else {
                    result += (m / a) * (n / a + 1);
                }
            } else {
                if (n % a == 0) {
                    result += (m / a + 1) * (n / a);
                } else {
                    result += (m / a + 1) * (n / a + 1);
                }
            }
            System.out.println(result);
        }
    }
}
