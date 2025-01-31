
import java.util.HashSet;
import java.util.Set;

/**
 * Solution for the "Making A Large Island" problem
 *
 * Problem Description: You are given an n x n binary matrix grid. A cell (r, c)
 * is called a land cell if grid[r][c] = 1 and a water cell if grid[r][c] = 0.
 * You may change at most one water cell's value to 1.
 *
 * The task is to find the size of the largest island after changing at most one
 * water cell to land. An island is a 4-directionally connected group of 1s.
 *
 * Time Complexity: O(n²) where n is the size of the grid Space Complexity:
 * O(n²) for storing island IDs and sizes
 */
class Solution {
    // Direction arrays for moving up, down, left, right

    private final int[] dr = new int[]{-1, 1, 0, 0};  // row directions
    private final int[] dc = new int[]{0, 0, -1, 1};  // column directions

    /**
     * Finds the size of the largest possible island after changing at most one
     * water cell to land.
     *
     * @param grid The input binary matrix
     * @return The size of the largest possible island
     */
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] islandId = new int[n][n];
        int[] islandSize = new int[n * n + 1]; // To store sizes of islands
        int id = 1;
        int maxSize = 0;

        // Assign unique IDs to each island and calculate their sizes
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1 && islandId[r][c] == 0) {
                    int size = dfs(grid, islandId, r, c, id);
                    islandSize[id] = size; // Store the size of the island
                    maxSize = Math.max(maxSize, size);
                    id++;
                }
            }
        }

        // Evaluate each 0 to see if changing it to 1 can form a larger island
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    Set<Integer> neighborIds = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                            neighborIds.add(islandId[nr][nc]);
                        }
                    }
                    int total = 1; // Count the current 0 we are changing to 1
                    for (int neighborId : neighborIds) {
                        total += islandSize[neighborId];
                    }
                    maxSize = Math.max(maxSize, total);
                }
            }
        }

        return maxSize;
    }

    /**
     * Performs depth-first search to mark all cells of an island with the same
     * ID and returns the size of the island.
     *
     * @param grid The input binary matrix
     * @param islandId Matrix to store island IDs
     * @param r Current row
     * @param c Current column
     * @param id Current island ID
     * @return Size of the island
     */
    private int dfs(int[][] grid, int[][] islandId, int r, int c, int id) {
        int n = grid.length;
        if (r < 0 || r >= n || c < 0 || c >= n || grid[r][c] == 0 || islandId[r][c] != 0) {
            return 0;
        }
        islandId[r][c] = id; // Mark the cell with the island ID
        int size = 1; // Count this cell
        for (int i = 0; i < 4; i++) {
            size += dfs(grid, islandId, r + dr[i], c + dc[i], id);
        }
        return size;
    }

    /**
     * Test cases for the largestIsland method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Single cell conversion
        int[][] grid1 = {{1, 0}, {0, 1}};
        assert solution.largestIsland(grid1) == 3;

        // Test Case 2: Already maximum size
        int[][] grid2 = {{1, 1}, {1, 1}};
        assert solution.largestIsland(grid2) == 4;

        // Test Case 3: No land cells
        int[][] grid3 = {{0, 0}, {0, 0}};
        assert solution.largestIsland(grid3) == 1;

        // Test Case 4: Complex case
        int[][] grid4 = {
            {1, 1, 0, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 1, 1, 1}
        };
        assert solution.largestIsland(grid4) == 7;

        System.out.println("All test cases passed!");
    }
}
