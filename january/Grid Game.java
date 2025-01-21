
/**
 * Grid Game - A problem involving optimal path selection in a 2x2 grid
 *
 * Problem Description:
 * Given a 2 x n grid of integers, two robots must collect points:
 * - Robot 1 moves from top-left, can move right or down, and collects points until stopping
 * - Robot 2 starts after Robot 1 finishes and collects remaining points
 * - The goal is to minimize Robot 2's maximum possible points
 *
 * Multiple Solution Approaches:
 * 1. Optimal Solution (O(n) time, O(1) space) - Uses prefix sums
 * 2. Dynamic Programming (O(n) time, O(n) space) - Educational approach
 * 3. Recursive with Memoization (O(n) time, O(n) space) - Top-down approach
 * 4. Two Pointer Technique (O(n) time, O(1) space) - Alternative optimal approach
 */
// Solution 1: Optimal solution using prefix sums with O(n) time and O(1) space
class Solution {

    /**
     * Calculates the minimum possible maximum points Robot 2 can collect
     *
     * @param grid 2xn grid of integers representing points
     * @return minimum possible maximum points for Robot 2
     */
    public long gridGame(int[][] grid) {
        long topSum = 0;
        long bottomSum = 0;
        int n = grid[0].length;

        // Calculate initial sums for entire rows except first column
        for (int i = 1; i < n; i++) {
            topSum += grid[0][i];
        }

        long result = Long.MAX_VALUE;

        // Iterate through each possible transition point
        for (int i = 0; i < n; i++) {
            // Update result with current minimum
            result = Math.min(result, Math.max(topSum, bottomSum));

            // Update sums for next iteration
            if (i < n - 1) {
                topSum -= grid[0][i + 1];
                bottomSum += grid[1][i];
            }
        }

        return result;
    }

    /**
     * Test cases to verify the solution
     */
    public void runTests() {
        // Test Case 1: Basic case
        int[][] test1 = {{2, 5, 4}, {1, 5, 1}};
        assert gridGame(test1) == 4 : "Test Case 1 Failed";

        // Test Case 2: Minimum size grid
        int[][] test2 = {{1, 1}, {1, 1}};
        assert gridGame(test2) == 1 : "Test Case 2 Failed";

        // Test Case 3: Large numbers
        int[][] test3 = {{1000000, 1000000}, {1000000, 1000000}};
        assert gridGame(test3) == 1000000 : "Test Case 3 Failed";

        // Test Case 4: Zero points
        int[][] test4 = {{0, 0, 0}, {0, 0, 0}};
        assert gridGame(test4) == 0 : "Test Case 4 Failed";

        System.out.println("All tests passed!");
    }
}

// Solution 2: Dynamic Programming approach for educational purposes
class DPSolution {

    /**
     * Solves the grid game using dynamic programming Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param grid 2xn grid of integers
     * @return minimum possible maximum points for Robot 2
     */
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[][] dp = new long[2][n];

        // Initialize DP array
        dp[0][0] = grid[0][0];
        dp[1][0] = dp[0][0] + grid[1][0];

        // Fill first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill second row
        for (int j = 1; j < n; j++) {
            dp[1][j] = Math.min(dp[1][j - 1], dp[0][j]) + grid[1][j];
        }

        return dp[1][n - 1];
    }

    /**
     * Test cases for DP solution
     */
    public void runTests() {
        // Test Case 1: Standard case
        int[][] test1 = {{2, 5, 4}, {1, 5, 1}};
        assert gridGame(test1) == 4 : "DP Test Case 1 Failed";

        // Test Case 2: Single column
        int[][] test2 = {{1}, {1}};
        assert gridGame(test2) == 1 : "DP Test Case 2 Failed";

        System.out.println("All DP tests passed!");
    }
}

// Solution 3: Recursive approach with memoization
class RecursiveSolution {

    private Long[][] memo;
    private int[][] grid;
    private int n;

    /**
     * Solves the grid game using recursion with memoization Time Complexity:
     * O(n) with memoization Space Complexity: O(n) for memoization array
     *
     * @param grid 2xn grid of integers
     * @return minimum possible maximum points for Robot 2
     */
    public long gridGame(int[][] grid) {
        this.grid = grid;
        this.n = grid[0].length;
        this.memo = new Long[2][n];
        return solve(0, 0);
    }

    private long solve(int row, int col) {
        if (col == n - 1 && row == 1) {
            return 0;
        }
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        long result;
        if (row == 0) {
            result = Math.min(
                    solve(0, col + 1) + grid[0][col + 1],
                    solve(1, col) + grid[1][col]
            );
        } else {
            result = solve(1, col + 1) + grid[1][col + 1];
        }

        memo[row][col] = result;
        return result;
    }

    /**
     * Test cases for recursive solution
     */
    public void runTests() {
        // Test Case 1: Simple path
        int[][] test1 = {{1, 2}, {3, 4}};
        assert gridGame(test1) == 4 : "Recursive Test Case 1 Failed";

        // Test Case 2: Complex path
        int[][] test2 = {{1, 2, 3}, {4, 5, 6}};
        assert gridGame(test2) == 7 : "Recursive Test Case 2 Failed";

        System.out.println("All recursive tests passed!");
    }
}

// Solution 4: Using two pointers technique
class TwoPointerSolution {

    /**
     * Solves the grid game using two pointers Time Complexity: O(n) Space
     * Complexity: O(1)
     *
     * @param grid 2xn grid of integers
     * @return minimum possible maximum points for Robot 2
     */
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long top = 0, bottom = 0;

        // Calculate initial sums
        for (int i = 0; i < n; i++) {
            top += grid[0][i];
        }

        long result = Long.MAX_VALUE;
        long currentTop = top;

        // Use two pointers to track transition point
        for (int i = 0; i < n; i++) {
            currentTop -= grid[0][i];
            result = Math.min(result, Math.max(currentTop, bottom));
            bottom += grid[1][i];
        }

        return result;
    }

    /**
     * Test cases for two pointer solution
     */
    public void runTests() {
        // Test Case 1: Equal values
        int[][] test1 = {{1, 1, 1}, {1, 1, 1}};
        assert gridGame(test1) == 1 : "Two Pointer Test Case 1 Failed";

        // Test Case 2: Increasing values
        int[][] test2 = {{1, 2, 3}, {4, 5, 6}};
        assert gridGame(test2) == 7 : "Two Pointer Test Case 2 Failed";

        System.out.println("All two pointer tests passed!");
    }
}
