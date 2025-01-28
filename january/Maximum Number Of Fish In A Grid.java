
/**
 * Solution class for finding the maximum number of fish in a grid.
 * LeetCode Problem: Maximum Number of Fish in a Grid
 *
 * Problem Description:
 * Given a 2D grid where each cell contains a number representing the number of fish in that cell,
 * find the maximum number of fish that can be caught by starting from any cell and moving in
 * four directions (up, down, left, right). You can only move to cells that contain fish (value > 0).
 *
 * Time Complexity: O(m * n) where m and n are the dimensions of the grid
 * Space Complexity: O(m * n) for the visited array in DFS
 */
class Solution {

    /**
     * Finds the maximum number of fish that can be caught starting from any
     * cell.
     *
     * @param grid 2D integer array representing fish counts in each cell
     * @return maximum number of fish that can be caught in a single connected
     * component
     * @throws IllegalArgumentException if grid is null or empty
     */
    public int findMaxFish(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid cannot be null or empty");
        }

        int maxFish = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    maxFish = Math.max(maxFish, dfs(grid, visited, i, j));
                }
            }
        }

        return maxFish;
    }

    /**
     * Performs depth-first search to count connected fish cells.
     *
     * @param grid 2D integer array representing fish counts
     * @param visited 2D boolean array tracking visited cells
     * @param r current row position
     * @param c current column position
     * @return total fish count in the connected component
     */
    private int dfs(int[][] grid, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;
        int fishCount = grid[r][c];

        // Explore all four directions
        fishCount += dfs(grid, visited, r + 1, c); // down
        fishCount += dfs(grid, visited, r - 1, c); // up
        fishCount += dfs(grid, visited, r, c + 1); // right
        fishCount += dfs(grid, visited, r, c - 1); // left

        return fishCount;
    }

    /**
     * Test cases for the solution.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Normal case
        int[][] grid1 = {
            {0, 2, 1, 0},
            {4, 0, 0, 3},
            {1, 0, 0, 4},
            {0, 3, 2, 0}
        };
        assert solution.findMaxFish(grid1) == 7 : "Test case 1 failed";

        // Test Case 2: Single cell
        int[][] grid2 = {{5}};
        assert solution.findMaxFish(grid2) == 5 : "Test case 2 failed";

        // Test Case 3: Empty cells
        int[][] grid3 = {{0, 0}, {0, 0}};
        assert solution.findMaxFish(grid3) == 0 : "Test case 3 failed";

        // Test Case 4: All cells with fish
        int[][] grid4 = {{1, 1}, {1, 1}};
        assert solution.findMaxFish(grid4) == 4 : "Test case 4 failed";

        System.out.println("All test cases passed!");
    }
}
