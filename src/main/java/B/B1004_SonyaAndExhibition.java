//package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class B1004_SonyaAndExhibition {

    public static void main(String[] args) throws IOException {
        int flowersCount;
        int visitorsCount;
        Segment[] visitorsSegments;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            flowersCount = Integer.parseInt(firstArgs[0]);
            visitorsCount = Integer.parseInt(firstArgs[1]);
            visitorsSegments = new Segment[visitorsCount];
            for (int i = 0; i < visitorsCount; i++) {
                String[] nextArgs = br.readLine().split(" ");
                visitorsSegments[i] = new Segment(
                        Integer.parseInt(nextArgs[0]) - 1,
                        Integer.parseInt(nextArgs[1]) - 1);
            }
        }
        int[] result = new int[flowersCount];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        Collections.sort(Arrays.asList(visitorsSegments));
        for (Segment segment : visitorsSegments) {
            putFlowersForSegment(segment, result);
        }
        StringBuilder sb = new StringBuilder();
        String filler = "0";
        for (int i = 0; i < result.length; i++) {
            if (result[i] == -1) {
                sb.append(filler);
                filler = filler.equals("0") ? "1" : "0";
            } else {
                sb.append(result[i]);
            }
        }
        StringBuilder solution = new StringBuilder();
        for (int i = 0; i < flowersCount; i++) {
            solution.append(filler);
            filler = filler.equals("0") ? "1" : "0";
        }
        System.out.println(solution.toString());
    }

    private static void putFlowersForSegment(Segment segment, int[] result) {
        for (int i = segment.start; i < segment.start + segment.size; i++) {
            if (result[i] == 0) {
                segment.rosesCount--;
            }
            if (result[i] == 1) {
                segment.lilyCount--;
            }
        }
        if (segment.remainder == 1 && (segment.rosesCount == -1 || segment.lilyCount == -1)) {
            segment.remainder--;
        }
        if (segment.rosesCount > 0 || segment.lilyCount > 0 || segment.remainder > 0) {
            for (int i = segment.start; i <= segment.end; i++) {
                if (result[i] == -1) {
                    if (segment.rosesCount > 0) {
                        result[i] = 0;
                        segment.rosesCount--;
                    } else if (segment.lilyCount > 0) {
                        result[i] = 1;
                        segment.lilyCount--;
                    } else if (segment.remainder > 0) {
                        result[i] = segment.lilyCount < segment.rosesCount ? 1 : 0;
                        segment.remainder--;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    static class Segment implements Comparable<Segment>{
        int start;
        int end;
        int size;
        int rosesCount;
        int lilyCount;
        int remainder;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
            this.size = end - start + 1;
            this.rosesCount = this.size / 2;
            this.lilyCount = this.size / 2;
            this.remainder = this.size % 2;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.size < o.size) {
                return -1;
            } else if (this.size > o.size) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

