package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A8_TrainAndPeter {
    public static void main(String[] args) throws IOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 3; i++) {
                strings.add(br.readLine());
            }
        }
        String there = strings.get(0);
        String back = new StringBuilder(there).reverse().toString();
        String first = strings.get(1);
        String second = strings.get(2);
        String result;
        int thereFirst = containsSubPath(there, first);
        int backFirst = containsSubPath(back, first);
        if (thereFirst != -1 && containsSubPath(there.substring(thereFirst, there.length() - 1), second) != -1) {
            if (backFirst != -1 && containsSubPath(back.substring(backFirst, back.length() - 1), second) != -1) {
                result = "both";
            } else {
                result = "forward";
            }
        } else if (backFirst != -1 && containsSubPath(back.substring(backFirst, back.length() - 1), second) != -1) {
            result = "backward";
        } else {
            result = "fantasy";
        }
        System.out.println(result);
    }

    private static int containsSubPath(String path, String subPath) {
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == subPath.charAt(i)) {
                int count = 0;
                int lastIndex = 0;
                for (int j = i; j < j + subPath.length(); j++) {
                    if (path.charAt(j) != subPath.charAt(j - i)) {
                        break;
                    }
                    lastIndex = j;
                }
                if (count == subPath.length()) {
                    return lastIndex;
                }
            }
        }
        return -1;
    }
}
