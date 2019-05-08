package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B1000_LightItUp {
    public static void main(String[] args) throws IOException {
        List<Range> ranges = new ArrayList<>();
        int[] points;
        int count;
        int upperLimit;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            count = Integer.parseInt(firstArgs[0]);
            points = new int[count + 2];
            points[0] = 0;
            upperLimit = Integer.parseInt(firstArgs[1]);
            String[] program = br.readLine().split(" ");
            for (int i = 0; i < program.length; i++) {
                points[i + 1] = Integer.parseInt(program[i]);
            }
            points[points.length - 1] = upperLimit;
        }
        ranges.add(new Range(true, points[1], points[0], points[1]));
        boolean on = false;
        for (int i = 1; i < points.length - 1; i++) {
            if (!on) {
                ranges.add(new Range(false, ranges.get(i - 1).totalOnSum, points[i], points[i + 1]));
                on = true;
            } else {
                ranges.add(new Range(true, ranges.get(i - 1).totalOnSum + points[i + 1] - points[i], points[i], points[i + 1]));
                on = false;
            }
        }
        int maxOnTime = ranges.get(ranges.size() - 1).totalOnSum;
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).end - ranges.get(i).start > 1) {
                int possibleMaxOnTime = recalculateTotalOnSum(ranges, i);
                if (maxOnTime < possibleMaxOnTime) {
                    maxOnTime = possibleMaxOnTime;
                }
            }
        }
        System.out.println(maxOnTime);
    }

    private static int recalculateTotalOnSum(List<Range> ranges, int currentRangeIndex) {
        int totalSum = 0;
        Range currentRange = ranges.get(currentRangeIndex);
        if (currentRangeIndex != 0) {
            totalSum += ranges.get(currentRangeIndex - 1).totalOnSum;
        }
        if (currentRange.on) {
            totalSum += currentRangeIndex == 0 ? currentRange.totalOnSum - 1 : currentRange.totalOnSum - ranges.get(currentRangeIndex - 1).totalOnSum - 1;
        } else {
            totalSum += currentRange.end - currentRange.start - 1;
        }
        if (currentRangeIndex != ranges.size() - 1) {
            int oldTotalOnSumForTheRestOfRanges = ranges.get(ranges.size() - 1).totalOnSum - ranges.get(currentRangeIndex).totalOnSum;
            int newTotalOnSumForTheRestOfRanges = ranges.get(ranges.size() - 1).end - ranges.get(currentRangeIndex).end - oldTotalOnSumForTheRestOfRanges;
            totalSum += newTotalOnSumForTheRestOfRanges;
        }
        return totalSum;
    }

    static class Range {
        boolean on;
        int totalOnSum;
        int start;
        int end;

        public Range(boolean on, int totalOnSum, int start, int end) {
            this.on = on;
            this.totalOnSum = totalOnSum;
            this.start = start;
            this.end = end;
        }
    }
}
