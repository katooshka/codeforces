package B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_TheLeastRoundWay {

    private static int minPathMultiplierCount;

    public static void main(String args[]) throws IOException {
        int matrixSize;
        int[][] matrix;
        boolean hasZero = false;
        int iZero = -1;
        int jZero = -1;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            matrixSize = Integer.parseInt(br.readLine());
            matrix = new int[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < matrixSize; j++) {
                    int current = Integer.parseInt(tokenizer.nextToken());
                    matrix[i][j] = current;
                    if (current == 0) {
                        hasZero = true;
                        iZero = i;
                        jZero = j;
                    }
                }
            }
        }
        String path;
        if (hasZero) {
            path = findPathWithMinNumberCount(replaceZero(matrix));
            if (minPathMultiplierCount > 0) {
                minPathMultiplierCount = 1;
                path = generateZeroPath(iZero, jZero, matrix.length);
            }
        } else {
            path = findPathWithMinNumberCount(matrix);
        }
        System.out.println(minPathMultiplierCount);
        System.out.println(path);
    }

    private static String generateZeroPath(int iZero, int jZero, int matrixLength) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < iZero; i++) {
            path.append("D");
        }
        for (int i = 0; i < jZero; i++) {
            path.append("R");
        }
        for (int i = iZero; i < matrixLength - 1; i++) {
            path.append("D");
        }
        for (int i = jZero; i < matrixLength - 1; i++) {
            path.append("R");
        }
        return path.toString();
    }


    private static String findPathWithMinNumberCount(int[][] matrix) {
        int[][] twoMatrix = new int[matrix.length + 1][matrix.length + 1];
        int[][] fiveMatrix = new int[matrix.length + 1][matrix.length + 1];
        twoMatrix[1][1] = countTwo(matrix[0][0]);
        fiveMatrix[1][1] = countFive(matrix[0][0]);
        for (int i = 1; i < matrix.length; i++) {
            twoMatrix[1][i + 1] = countTwo(matrix[0][i]) + twoMatrix[1][i];
            twoMatrix[i + 1][1] = countTwo(matrix[i][0]) + twoMatrix[i][1];
            fiveMatrix[1][i + 1] = countFive(matrix[0][i]) + fiveMatrix[1][i];
            fiveMatrix[i + 1][1] = countFive(matrix[i][0]) + fiveMatrix[i][1];
        }
        for (int i = 2; i < twoMatrix.length; i++) {
            for (int j = 2; j < twoMatrix[0].length; j++) {
                twoMatrix[i][j] = Math.min(twoMatrix[i - 1][j], twoMatrix[i][j - 1]) + countTwo(matrix[i - 1][j - 1]);
                fiveMatrix[i][j] = Math.min(fiveMatrix[i - 1][j], fiveMatrix[i][j - 1]) + countFive(matrix[i - 1][j - 1]);
            }
        }
        int twoMultiplierCount = twoMatrix[twoMatrix.length - 1][twoMatrix.length - 1];
        int fiveMultiplierCount = fiveMatrix[fiveMatrix.length - 1][fiveMatrix.length - 1];
        if (twoMultiplierCount < fiveMultiplierCount) {
            minPathMultiplierCount = twoMultiplierCount;
            return findPath(twoMatrix);
        } else {
            minPathMultiplierCount = fiveMultiplierCount;
            return findPath(fiveMatrix);
        }
    }

    private static int[][] replaceZero(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 10;
                }
            }
        }
        return matrix;
    }

    private static String findPath(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[0][i] = Integer.MAX_VALUE;
            matrix[i][0] = Integer.MAX_VALUE;
        }
        StringBuilder pathBuilder = new StringBuilder();
        int i = matrix.length - 1;
        int j = matrix.length - 1;
        while (i != 1 || j != 1) {
            if (matrix[i][j - 1] < matrix[i - 1][j]) {
                pathBuilder.append("R");
                j = j - 1;
            } else {
                pathBuilder.append("D");
                i = i - 1;
            }
        }
        return pathBuilder.reverse().toString();
    }

    private static int countMultipliers(int multiplier, int number) {
        if (number == 0) {
            return 0;
        }
        int count = 0;
        while (number % multiplier == 0) {
            number = number / multiplier;
            count++;
        }
        return count;
    }

    private static int countTwo(int number) {
        if (number == 0) {
            return 0;
        }
        int count = 0;
        if (number % 65536 == 0) {
            count += 16;
            number /= 65536;
        }
        if (number % 256 == 0){
            count += 8;
            number /= 256;
        }
        if (number % 16 == 0){
            count += 4;
            number /= 16;
        }
        if (number % 4 == 0){
            count += 2;
            number /= 4;
        }
        if (number % 2 == 0){
            count += 1;
        }
        return count;
    }


    private static int countFive(int number) {
        if (number == 0) {
            return 0;
        }
        int count = 0;
        if (number % 390625 == 0){
            count += 8;
            number /= 390625;
        }
        if (number % 625 == 0){
            count += 4;
            number /= 625;
        }
        if (number % 25 == 0){
            count += 2;
            number /= 25;
        }
        if (number % 5 == 0){
            count += 1;
        }
        return count;
    }
}