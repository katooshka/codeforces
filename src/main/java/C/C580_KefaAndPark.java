package C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C580_KefaAndPark {
    public static void main(String[] args) throws IOException {
        int vertexCount;
        int catsMaxNumber;
        Map<Integer, Node> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstArgs = br.readLine().split(" ");
            vertexCount = Integer.parseInt(firstArgs[0]);
            catsMaxNumber = Integer.parseInt(firstArgs[1]);
            String[] secondArgs = br.readLine().split(" ");
            for (int i = 0; i < secondArgs.length; i++) {
                boolean hasCat = Integer.parseInt(secondArgs[i]) == 1;
                map.put(i + 1, new Node(hasCat, new ArrayList<>(), 0, null, false));
            }
            for (int i = 0; i < vertexCount - 1; i++) {
                String[] edge = br.readLine().split(" ");
                int first = Integer.parseInt(edge[0]);
                int second = Integer.parseInt(edge[1]);
                map.get(first).outgoingNodes.add(map.get(second));
                map.get(second).outgoingNodes.add(map.get(first));
            }
            System.out.println(findPaths(map.get(1), catsMaxNumber));
        }
    }

    private static int findPaths(Node root, int catsMaxNumber) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        if (root.hasCat) {
            root.consecutiveCats = 1;
        }
        int count = 0;
        while (!queue.isEmpty()) {
            Node currentNode = queue.getFirst();
            if (currentNode.previousNode != null && currentNode.hasCat) {
                currentNode.consecutiveCats = currentNode.previousNode.consecutiveCats + 1;
            }
            if (currentNode.consecutiveCats <= catsMaxNumber) {
                if (currentNode.outgoingNodes.isEmpty()) {
                    count++;
                } else {
                    for (Node outgoingNode : currentNode.outgoingNodes) {
                        int visitedNodesCount = 0;
                        if (!outgoingNode.visited) {
                            outgoingNode.previousNode = currentNode;
                            queue.add(outgoingNode);
                        } else {
                            visitedNodesCount++;
                        }
                        if (visitedNodesCount == currentNode.outgoingNodes.size()) {
                            count++;
                        }
                    }
                }
            }
            currentNode.visited = true;
            queue.removeFirst();
        }
        return count;
    }

    static class Node {
        boolean hasCat;
        List<Node> outgoingNodes;
        int consecutiveCats;
        Node previousNode;
        boolean visited;

        public Node(boolean hasCat, List<Node> outgoingNodes, int consecutiveCats, Node previousNode, boolean visited) {
            this.hasCat = hasCat;
            this.outgoingNodes = outgoingNodes;
            this.consecutiveCats = consecutiveCats;
            this.previousNode = previousNode;
            this.visited = visited;
        }
    }
}
