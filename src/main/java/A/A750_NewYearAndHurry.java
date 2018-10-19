package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A750_NewYearAndHurry {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            int problemsNumber = Integer.parseInt(str.split(" ")[0]);
            int commuteTime = Integer.parseInt(str.split(" ")[1]);
            int totalTime = 240;
            int timeForProblems = totalTime - commuteTime;
            int timeCount = 0;
            if (timeForProblems < 5) {
                System.out.println(0);
                return;
            }
            for (int i = 1; i <= problemsNumber; i++) {
                timeCount = timeCount + i * 5;
                if (timeCount > timeForProblems) {
                    System.out.println(i - 1);
                    return;
                }
            }
            System.out.println(problemsNumber);
        }
    }
}
