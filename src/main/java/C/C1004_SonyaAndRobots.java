package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class C1004_SonyaAndRobots {
    public static void main(String[] args) throws IOException {
        int count;
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(br.readLine().split(" ")[0]);
            String[] numbersStrings = br.readLine().split(" ");
            numbers = Arrays.stream(numbersStrings).mapToInt(Integer::parseInt).toArray();
        }
//        System.out.println(slowSolution(numbers));
        System.out.println(solution(numbers));
    }

    private static int slowSolution(int[] numbers) {
        Set<Pair> result = new HashSet<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                Pair currentPair = new Pair(numbers[i], numbers[j]);
                if (!result.contains(currentPair)) {
                    result.add(currentPair);
                }
            }
        }
        return result.size();
    }

    private static long solution(int[] numbers) {
        if (numbers.length == 1) {
            return 0;
        }
        int[] unique_numbers = new int[numbers.length];
        unique_numbers[unique_numbers.length - 1] = 1;
        Set<Integer> checkedNumbers = new HashSet<>();
        checkedNumbers.add(numbers[numbers.length - 1]);
        for (int i = numbers.length - 2; i >= 0; i--) {
            if (!checkedNumbers.contains(numbers[i])) {
                checkedNumbers.add(numbers[i]);
                unique_numbers[i] = unique_numbers[i + 1] + 1;
            } else {
                unique_numbers[i] = unique_numbers[i + 1];
            }
        }
        long result = 0;
        Set<Integer> processedNumbers = new HashSet<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            if (!processedNumbers.contains(numbers[i])) {
                processedNumbers.add(numbers[i]);
                result += unique_numbers[i + 1];
            }
        }
        return result;
    }


    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != pair.first) return false;
            return second == pair.second;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
}
