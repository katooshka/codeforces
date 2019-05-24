package D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class D1009_RelativelyPrimeGraph {
    public static void main(String[] args) throws IOException {
        int verticesCount;
        int edgesCount;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = br.readLine().split(" ");
            verticesCount = Integer.parseInt(line[0]);
            edgesCount = Integer.parseInt(line[1]);
        }
        if (edgesCount < verticesCount - 1) {
            System.out.println("Impossible");
            return;
        }
        int count = edgesCount;
        List<Pair> edges = new ArrayList<>();
        for (int i = 1; i <= verticesCount; i++) {
            for (int j = i + 1; j <= verticesCount; j++) {
                if (count <= 0) {
                    System.out.println(returnResult(edges));
                    return;
                }
                if (findGCD(i, j) == 1) {
                    count--;
                    edges.add(new Pair(i, j));
                }
            }
        }
        if (count > 0) {
            System.out.println("Impossible");
        } else {
            System.out.println(returnResult(edges));
        }
    }

    public static int findGCD(int i, int j) {
        int number = Math.max(i, j);
        int divider = Math.min(i, j);
        int gcd = divider;
        while (number % divider != 0) {
            gcd = number % divider;
            int remainder = number % divider;
            number = divider;
            divider = remainder;
        }
        return gcd;
    }

    public static String returnResult(List<Pair> edges) {
        StringBuilder sb = new StringBuilder();
        sb.append("Possible\n");
        for (Pair edge : edges) {
            sb.append(edge.toString());
        }
        return sb.toString();
    }

    static class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + " " + end + "\n";
        }
    }
}
