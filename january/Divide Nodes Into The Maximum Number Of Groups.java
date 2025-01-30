
import java.util.*;

/**
 * Solution class for dividing nodes into maximum number of groups LeetCode
 * Problem: Divide Nodes Into the Maximum Number of Groups
 */
class Solution {

    /**
     * Adjacency list representation of the graph
     */
    private List<List<Integer>> graph;

    /**
     * Finds the maximum number of groups that nodes can be divided into
     *
     * @param n Number of nodes in the graph (1-based indexing)
     * @param edges Array of edges where each edge is represented as [node1,
     * node2]
     * @return Maximum possible number of groups, or -1 if impossible
     */
    public int magnificentSets(int n, int[][] edges) {
        // Build adjacency list
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Array to track visited nodes and their components
        boolean[] visited = new boolean[n + 1];
        int result = 0;

        // Process each unvisited node
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                // Find all nodes in current component
                List<Integer> component = new ArrayList<>();
                dfs(i, visited, component);

                // Find maximum groups in current component
                int maxGroups = findMaxGroups(component);
                if (maxGroups == -1) {
                    return -1;
                }
                result += maxGroups;
            }
        }

        return result;
    }

    /**
     * Performs Depth-First Search to find connected components
     *
     * @param node Current node being visited
     * @param visited Array tracking visited nodes
     * @param component List to store nodes in current component
     */
    private void dfs(int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, component);
            }
        }
    }

    /**
     * Finds maximum possible groups in a component
     *
     * @param component List of nodes in the current component
     * @return Maximum number of groups possible, or -1 if impossible
     */
    private int findMaxGroups(List<Integer> component) {
        int maxGroups = 0;
        int[] distance = new int[graph.size()];

        // Try each node in component as starting point
        for (int start : component) {
            Arrays.fill(distance, -1);
            int groups = bfs(start, distance);
            if (groups == -1) {
                return -1;
            }
            maxGroups = Math.max(maxGroups, groups);
        }

        return maxGroups;
    }

    /**
     * Performs Breadth-First Search to find maximum possible groups
     *
     * @param start Starting node for BFS
     * @param distance Array to track distances from start node
     * @return Number of groups possible from this start node, or -1 if
     * impossible
     */
    private int bfs(int start, int[] distance) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distance[start] = 0;
        int maxDist = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph.get(curr)) {
                if (distance[next] == -1) {
                    distance[next] = distance[curr] + 1;
                    maxDist = Math.max(maxDist, distance[next]);
                    queue.offer(next);
                } else if (Math.abs(distance[next] - distance[curr]) != 1) {
                    return -1;
                }
            }
        }

        return maxDist + 1;
    }

    /**
     * Test cases for the magnificentSets method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Simple graph with 3 nodes
        int[][] edges1 = {{1, 2}, {2, 3}};
        assert solution.magnificentSets(3, edges1) == 2 : "Test case 1 failed";

        // Test Case 2: Cycle graph with 4 nodes
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {4, 1}};
        assert solution.magnificentSets(4, edges2) == 4 : "Test case 2 failed";

        // Test Case 3: Impossible grouping
        int[][] edges3 = {{1, 2}, {2, 3}, {3, 1}};
        assert solution.magnificentSets(3, edges3) == -1 : "Test case 3 failed";

        // Test Case 4: Disconnected components
        int[][] edges4 = {{1, 2}, {3, 4}};
        assert solution.magnificentSets(4, edges4) == 2 : "Test case 4 failed";

        // Test Case 5: Single node
        int[][] edges5 = {};
        assert solution.magnificentSets(1, edges5) == 1 : "Test case 5 failed";

        System.out.println("All test cases passed!");
    }
}
