package A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A7_KalevitchAndChess {
    public static void main(String[] args) throws IOException {
        List<String> rows = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 8; i++) {
                rows.add(br.readLine());
            }
        }
        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            for (String row : rows) {
                sb.append(row.charAt(i));
            }
            columns.add(sb.toString());
        }
        int countColumns = countCells(columns);
        int countRows = countCells(rows);
        if (countColumns == 8 && countRows == 8) {
            System.out.println(8);
        } else {
            System.out.println(countColumns + countRows);
        }
    }

    private static int countCells(List<String> lines) {
        int countCells = 0;
        for (String line : lines) {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                if (line.charAt(i) == 'B') {
                    count++;
                }
            }
            if (count == 8) {
                countCells++;
            }
        }
        return countCells;
    }
}
