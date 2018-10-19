package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A2_Winner {

    public static void main(String[] args) throws IOException {
        List<Result> results = new ArrayList<>();
        int roundsNumber;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            roundsNumber = Integer.parseInt(br.readLine());
            for (int i = 0; i < roundsNumber; i++) {
                String[] entry = br.readLine().split(" ");
                results.add(new Result(entry[0], Integer.parseInt(entry[1])));
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for (Result result : results) {
            if (!map.containsKey(result.name)) {
                map.put(result.name, result.score);
            } else {
                map.put(result.name, map.get(result.name) + result.score);
            }
        }
        int maxScore = -1000;
        for (Integer integer : map.values()) {
            if (integer > maxScore) {
                maxScore = integer;
            }
        }
        List<String> best_players = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxScore) {
                best_players.add(entry.getKey());
            }
        }
        Map<String, Integer> filteredMap = new HashMap<>();
        for (Result result : results) {
            if (best_players.contains(result.name)) {
                if (!filteredMap.containsKey(result.name)) {
                    filteredMap.put(result.name, result.score);
                } else {
                    filteredMap.put(result.name, filteredMap.get(result.name) + result.score);
                }
                if (filteredMap.get(result.name) >= maxScore) {
                    System.out.println(result.name);
                    return;
                }
            }
        }
    }

    static class Result {
        String name;
        int score;

        Result(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
