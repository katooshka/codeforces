package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1009_MinimumTernaryString {
    public static void main(String[] args) throws IOException {
        String string;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            string = br.readLine();
        }

        StringBuilder firstPart = new StringBuilder();
        StringBuilder secondPart = new StringBuilder();
        int i = 0;
        int countZero = 0;
        int countOne = 0;

        while (i < string.length() && string.charAt(i) != '2') {
            if (string.charAt(i) == '0') {
                countZero++;
            } else {
                countOne++;
            }
            i++;
        }
        for (int j = 0; j < countZero; j++) {
            firstPart.append('0');
        }
        for (int j = 0; j < countOne; j++) {
            firstPart.append('1');
        }
        for (int j = i; j < string.length(); j++) {
            if (string.charAt(j) == '0') {
                secondPart.append('0');
            } else if (string.charAt(j) == '2') {
                secondPart.append('2');
            } else {
                firstPart.append('1');
            }
        }
        System.out.println(firstPart.append(secondPart));
    }
}
