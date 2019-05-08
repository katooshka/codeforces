package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.abs;

public class C1009_AnnoyingPresent {
    public static void main(String[] args) throws IOException {
        long[] arr;
        Change[] changes;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            arr = new long[Integer.parseInt(firstArgs[0])];
            changes = new Change[Integer.parseInt(firstArgs[1])];
            for (int i = 0; i < changes.length; i++) {
                String[] nextArgs = br.readLine().split(" ");
                changes[i] = new Change(Integer.parseInt(nextArgs[0]), Integer.parseInt(nextArgs[1]));
            }
        }
        int negativeDIndex = arr.length / 2;
        int positiveDIndex = arr.length - 1;
        long sumX = Arrays.stream(changes).map(x -> x.x).reduce(0, Integer :: sum);
        long sumDPositive = Arrays.stream(changes).map(x -> x.d).filter(x -> x >= 0).reduce(0, Integer :: sum);
        long sumDNegative = Arrays.stream(changes).map(x -> x.d).filter(x -> x < 0).reduce(0, Integer :: sum);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sumX + sumDPositive * abs(i - positiveDIndex) + sumDNegative * abs(i - negativeDIndex);
        }
        double result = Arrays.stream(arr).average().getAsDouble();
        System.out.println(result);
    }

    static class Change {
        int x;
        int d;

        public Change(int x, int d) {
            this.x = x;
            this.d = d;
        }
    }
}
