
/**
 * Solution for LeetCode Problem: Count Servers that Communicate
 *
 * Problem Description:
 * You are given a map of servers represented by an m x n integer matrix 'grid' where:
 * - grid[i][j] = 1 represents an active server
 * - grid[i][j] = 0 represents an empty place
 * Two servers can communicate if they are on the same row or column.
 * Return the number of servers that can communicate with at least one other server.
 *
 * Approach:
 * 1. Count servers in each row and column using two arrays
 * 2. For each server, check if its row or column has more than one server
 * 3. If yes, it can communicate with at least one other server
 *
 * Time Complexity: O(m*n) where m and n are dimensions of the grid
 * Space Complexity: O(m+n) for storing row and column counts
 */
class Solution {

    /**
     * Counts the number of servers that can communicate with at least one other
     * server.
     *
     * @param grid 2D integer array representing the server map
     * @return number of servers that can communicate
     */
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Arrays to count servers in each row and column
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // First pass: count servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        // Second pass: count servers that can communicate
        int communicatingServers = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // A server can communicate if its row or column has more than 1 server
                    if (rowCount[i] > 1 || colCount[j] > 1) {
                        communicatingServers++;
                    }
                }
            }
        }

        return communicatingServers;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic case with communicating servers
        int[][] grid1 = {{1, 0}, {1, 1}};
        assert solution.countServers(grid1) == 3 : "Test Case 1 Failed";

        // Test Case 2: Isolated server
        int[][] grid2 = {{1, 0, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}};
        assert solution.countServers(grid2) == 1 : "Test Case 2 Failed";

        // Test Case 3: All servers communicate
        int[][] grid3 = {{1, 1, 1, 1}, {1, 1, 1, 1}};
        assert solution.countServers(grid3) == 8 : "Test Case 3 Failed";

        // Test Case 4: No servers
        int[][] grid4 = {{0, 0}, {0, 0}};
        assert solution.countServers(grid4) == 0 : "Test Case 4 Failed";

        // Test Case 5: Single server
        int[][] grid5 = {{1}};
        assert solution.countServers(grid5) == 0 : "Test Case 5 Failed";

        System.out.println("All test cases passed!");
    }
}
