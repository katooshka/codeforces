package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class A1008_Romaji {
    public static void main(String[] args) throws IOException {
        char[] string;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            string = br.readLine().toCharArray();
        }

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('u');
        vowels.add('i');
        vowels.add('o');
        vowels.add('e');

        for (int i = 0; i < string.length - 1;) {
            if (!vowels.contains(string[i])) {
                if (string[i] == 'n') {
                    i++;
                } else {
                    if (!vowels.contains(string[i + 1])) {
                        System.out.println("NO");
                        return;
                    }
                    i++;
                }
            } else {
                i++;
            }
        }
        if (!vowels.contains(string[string.length - 1]) && string[string.length - 1] != 'n') {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}
