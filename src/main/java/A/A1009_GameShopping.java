//package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A1009_GameShopping {
    public static void main(String[] args) throws IOException {
        int gamesCount;
        int notesCount;
        List<Integer> games;
        List<Integer> notes;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            gamesCount = Integer.parseInt(firstArgs[0]);
            notesCount = Integer.parseInt(firstArgs[1]);
            games = Arrays.stream(br.readLine().split(" ")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
            notes = Arrays.stream(br.readLine().split(" ")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        }
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < gamesCount && j < notesCount) {
            if (notes.get(j) < games.get(i)) {
                i++;
            } else {
                i++;
                j++;
                count++;
            }
        }
        System.out.println(count);
    }
}
