package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C510_FoxAndNames {

    private static boolean isImpossible = false;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int wordsNumber = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < wordsNumber; i++) {
                words.add(br.readLine());
            }
            Map<Character, List<Character>> constructedTree = constructInitialTree(words);
            if (isImpossible) {
                System.out.println("Impossible");
            } else {
                TopologicalSorting sorting = new TopologicalSorting();
                List<Character> sortedTree = sorting.sortTree(constructedTree);
                if (sorting.foundCycle) {
                    System.out.println("Impossible");
                } else {
                    Collections.reverse(sortedTree);
                    for (Character c : sortedTree) {
                        System.out.print(c);
                    }
                }
            }
        }
    }

    private static Map<Character, List<Character>> constructInitialTree(List<String> words) {
        Map<Character, List<Character>> tree = new LinkedHashMap<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = letters.length() - 1; i >= 0; i--) {
            tree.put(letters.charAt(i), new ArrayList<>());
        }
        for (int i = 0; i < words.size() - 1; i++) {
            if (isImpossible) break;
            String firstWord = words.get(i);
            String secondWord = words.get(i + 1);
            int j = 0;
            int minLength = Math.min(firstWord.length(), secondWord.length());
            while (j < minLength && firstWord.charAt(j) == secondWord.charAt(j)) {
                j++;
            }
            if (j == minLength && j == secondWord.length()) {
                isImpossible = true;
            } else if (j < minLength) {
                tree.get(firstWord.charAt(j)).add(secondWord.charAt(j));
            }
        }
        return tree;
    }

    static class TopologicalSorting {
        private boolean foundCycle = false;

        private List<Character> sortTree(Map<Character, List<Character>> tree) {
            Map<Character, Stage> nodeProcessingStage = new HashMap<>();
            for (Character c : tree.keySet()) {
                nodeProcessingStage.put(c, Stage.WHITE);
            }
            List<Character> result = new ArrayList<>();
            for (Character character : tree.keySet()) {
                if (nodeProcessingStage.get(character).equals(Stage.WHITE)) {
                    processNode(tree, nodeProcessingStage, character, result);
                }
            }
            return result;
        }

        private void processNode(Map<Character, List<Character>> tree, Map<Character, Stage> nodeProcessingStage, Character c, List<Character> result) {
            if (foundCycle) return;
            if (tree.get(c).isEmpty()) {
                nodeProcessingStage.put(c, Stage.BLACK);
                result.add(c);
                return;
            }
            nodeProcessingStage.put(c, Stage.GREY);
            for (Character character : tree.get(c)) {
                if (nodeProcessingStage.get(character) == (Stage.WHITE)) {
                    processNode(tree, nodeProcessingStage, character, result);
                } else if (nodeProcessingStage.get(character) == Stage.GREY) {
                    foundCycle = true;
                    return;
                }
            }
            nodeProcessingStage.put(c, Stage.BLACK);
            result.add(c);
        }

        private enum Stage {
            WHITE, GREY, BLACK
        }
    }
}
