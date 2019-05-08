package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1003_BinaryStringConstructing {

    public static void main(String[] args) throws IOException {
        int zeroCount;
        int oneCount;
        int indexCount;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            zeroCount = Integer.parseInt(numbers[0]);
            oneCount = Integer.parseInt(numbers[1]);
            indexCount = Integer.parseInt(numbers[2]);
        }
        int mainSecSize = (indexCount + 1) / 2;
        StringBuilder sb = new StringBuilder();
        if (indexCount % 2 == 0) {
            if (zeroCount < oneCount) {
                String main = "10";
                for (int i = 0; i < mainSecSize; i++) {
                    sb.append(main);
                }
                for (int i = 0; i < zeroCount - mainSecSize; i++) {
                    sb.append("0");
                }
                sb.append("1");
                for (int i = 0; i < oneCount - mainSecSize - 1; i++) {
                    sb.append("1");
                }
            } else {
                String main = "01";
                for (int i = 0; i < zeroCount - mainSecSize - 1; i++) {
                    sb.append("0");
                }
                for (int i = 0; i < mainSecSize; i++) {
                    sb.append(main);
                }
                for (int i = 0; i < oneCount - mainSecSize; i++) {
                    sb.append("1");
                }
                sb.append("0");
            }
        } else {
            if (zeroCount < oneCount) {
                String main = "10";
                for (int i = 0; i < oneCount - mainSecSize; i++) {
                    sb.append("1");
                }
                for (int i = 0; i < mainSecSize; i++) {
                    sb.append(main);
                }
                for (int i = 0; i < zeroCount - mainSecSize; i++) {
                    sb.append("0");
                }
            } else {
                String main = "01";
                for (int i = 0; i < zeroCount - mainSecSize; i++) {
                    sb.append("0");
                }
                for (int i = 0; i < mainSecSize; i++) {
                    sb.append(main);
                }
                for (int i = 0; i < oneCount - mainSecSize; i++) {
                    sb.append("1");
                }
            }
        }
        System.out.println(sb.toString());
    }
}
