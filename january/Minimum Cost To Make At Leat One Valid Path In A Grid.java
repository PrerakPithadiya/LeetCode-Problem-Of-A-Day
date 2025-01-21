
/**
 * LeetCode 1368 - Minimum Cost to Make at Least One Valid Path in a Grid
 *
 * Problem Description:
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently on this cell.
 * The signs are represented by numbers 1, 2, 3, and 4 indicating:
 * 1 - go to the right cell (i,j+1)
 * 2 - go to the left cell (i,j-1)
 * 3 - go to the lower cell (i+1,j)
 * 4 - go to the upper cell (i-1,j)
 * You can change the sign in any cell with a cost of 1.
 * Return the minimum cost to reach the bottom right cell (m-1,n-1) from the top left cell (0,0).
 *
 * Solution Approach:
 * - Uses 0-1 BFS (Breadth First Search) with a deque
 * - Maintains a cost matrix to track minimum cost to reach each cell
 * - Uses deque instead of priority queue for optimization:
 *   * Adds zero-cost moves to front of deque
 *   * Adds cost-1 moves to back of deque
 * - Time Complexity: O(m*n) where m,n are grid dimensions
 * - Space Complexity: O(m*n) for the costs array and deque
 */
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {

    // Define the 4 possible directions: right, left, down, up
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Track visited cells and their costs
        int[][] costs = new int[m][n];
        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        costs[0][0] = 0;

        // Use deque instead of priority queue for 0-1 BFS
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int row = curr[0];
            int col = curr[1];

            // Stop if we reached the target
            if (row == m - 1 && col == n - 1) {
                return costs[row][col];
            }

            // Check all four directions
            for (int i = 0; i < 4; i++) {
                int newRow = row + DIRECTIONS[i][0];
                int newCol = col + DIRECTIONS[i][1];

                // Skip if out of bounds
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                    continue;
                }

                // Calculate new cost
                // If current direction matches grid arrow, cost is 0, else 1
                int newCost = costs[row][col] + (grid[row][col] - 1 == i ? 0 : 1);

                // Update cost if we found a better path
                if (newCost < costs[newRow][newCol]) {
                    costs[newRow][newCol] = newCost;
                    // Add to front if cost is 0, back if cost is 1
                    if (grid[row][col] - 1 == i) {
                        deque.offerFirst(new int[]{newRow, newCol});
                    } else {
                        deque.offerLast(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return costs[m - 1][n - 1];
    }

    // Test cases
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Simple 2x2 grid
        int[][] grid1 = {{1, 1}, {2, 2}};
        assert solution.minCost(grid1) == 1 : "Test case 1 failed";

        // Test Case 2: Already valid path
        int[][] grid2 = {{1, 1, 1}, {2, 2, 1}};
        assert solution.minCost(grid2) == 0 : "Test case 2 failed";

        // Test Case 3: Complex path requiring multiple changes
        int[][] grid3 = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};
        assert solution.minCost(grid3) == 3 : "Test case 3 failed";

        // Test Case 4: Single cell grid
        int[][] grid4 = {{1}};
        assert solution.minCost(grid4) == 0 : "Test case 4 failed";

        System.out.println("All test cases passed!");
    }
}
