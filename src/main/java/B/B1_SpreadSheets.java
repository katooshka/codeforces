package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B1_SpreadSheets {
    public static void main(String[] args) throws IOException {
        List<String> initialDesignations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                initialDesignations.add(br.readLine());
            }
        }
        List<String> resultDesignations = new ArrayList<>();
        for (String initialDesignation : initialDesignations) {
            if (designationInSecondFormatType(initialDesignation)) {
                resultDesignations.add(convertFromSecondToFirstFormat(initialDesignation));
            } else {
                resultDesignations.add(convertFromFirstToSecondFormat(initialDesignation));
            }
        }
        for (String resultDesignation : resultDesignations) {
            System.out.println(resultDesignation);
        }
    }

    private static boolean designationInSecondFormatType(String designation) {
        Pattern p = Pattern.compile("R\\d+C\\d+");
        Matcher m = p.matcher(designation);
        return m.matches();
    }

    private static String convertFromFirstToSecondFormat(String designation) {
        String alphabet = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int columnCount = 0;
        int i = 0;
        while (Character.isAlphabetic(designation.charAt(i))) {
            columnCount = columnCount * 26 + alphabet.indexOf(designation.charAt(i));
            i++;
        }
        int rowCount = Integer.parseInt(designation.substring(i));
        StringBuilder sb = new StringBuilder();
        return sb.append("R").append(rowCount).append("C").append(columnCount).toString();
    }

    private static String convertFromSecondToFirstFormat(String designation) {
        int rowsCount = 0;
        int columnCount = 0;
        int i = 1;
        while (Character.isDigit(designation.charAt(i))) {
            rowsCount = rowsCount * 10 + Integer.parseInt(Character.toString(designation.charAt(i)));
            i++;
        }
        for (int j = i + 1; j < designation.length(); j++) {
            columnCount = columnCount * 10 + Integer.parseInt(Character.toString(designation.charAt(j)));
        }
        String alphabet = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder columnsSb = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        while (columnCount > 26) {
            int lastLetter = columnCount % 26;
            if (lastLetter != 0) {
                columnsSb.append(alphabet.charAt(lastLetter));
                columnCount = (columnCount - lastLetter) / 26;
            } else {
                columnsSb.append(("Z"));
                columnCount = (columnCount - 26) / 26;
            }
        }
        columnsSb.append(alphabet.charAt(columnCount));
        return sb.append(columnsSb.reverse()).append(rowsCount).toString();
    }
}
//1
//XZ250

//expected: 'R250C650', found: 'R250C624'