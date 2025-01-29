
/**
 * Solution for finding a redundant connection in an undirected graph.
 *
 * Problem: Given an undirected graph with n vertices and n edges, find one redundant edge
 * that can be removed to make the graph a tree (acyclic).
 *
 * Approach:
 * - Uses Union-Find (Disjoint Set) data structure with path compression
 * - Time Complexity: O(N * α(N)) where α is the inverse Ackermann function
 * - Space Complexity: O(N) for the parent array
 */
class Solution {

    /**
     * Finds a redundant connection in the given graph.
     *
     * @param edges 2D array where edges[i] = [node1, node2] represents an edge
     * between nodes
     * @return the redundant edge that can be removed, or empty array if none
     * found
     */
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[0];
        }

        int n = edges.length;
        int[] parent = new int[n + 1];

        // Initialize each node as its own parent
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Process each edge
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            // If nodes are already connected, this is a redundant edge
            if (find(parent, node1) == find(parent, node2)) {
                return edge;
            } else {
                union(parent, node1, node2);
            }
        }

        return new int[0];
    }

    /**
     * Finds the root parent of a node with path compression.
     *
     * @param parent array storing parent relationships
     * @param node the node to find the parent for
     * @return the root parent of the node
     */
    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]); // Path compression
        }
        return parent[node];
    }

    /**
     * Unions two nodes by setting the parent of node1's root to node2's root.
     *
     * @param parent array storing parent relationships
     * @param node1 first node to union
     * @param node2 second node to union
     */
    private void union(int[] parent, int node1, int node2) {
        parent[find(parent, node1)] = find(parent, node2);
    }

    /**
     * Test cases for the solution.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic cycle
        int[][] test1 = {{1, 2}, {1, 3}, {2, 3}};
        assert java.util.Arrays.equals(solution.findRedundantConnection(test1), new int[]{2, 3});

        // Test Case 2: Larger cycle
        int[][] test2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        assert java.util.Arrays.equals(solution.findRedundantConnection(test2), new int[]{1, 4});

        // Test Case 3: Multiple possible answers (returns the last occurring edge)
        int[][] test3 = {{1, 2}, {2, 3}, {3, 1}};
        assert java.util.Arrays.equals(solution.findRedundantConnection(test3), new int[]{3, 1});

        // Test Case 4: Empty input
        int[][] test4 = {};
        assert java.util.Arrays.equals(solution.findRedundantConnection(test4), new int[]{});

        System.out.println("All test cases passed!");
    }
}
