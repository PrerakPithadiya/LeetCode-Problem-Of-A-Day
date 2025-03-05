
/**
 * LeetCode Problem: Count Total Number of Colored Cells
 *
 * Problem Description:
 * Given a grid of uncolored cells and minutes n, simulate coloring process where:
 * - At minute 1: Color any cell blue
 * - Each subsequent minute: Color all uncolored cells that touch a blue cell
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 *
 * @author Prerak Pithadiya
 */
class Solution {

    /**
     * Calculates the total number of colored cells after n minutes
     *
     * @param n The number of minutes (1 <= n <= 10^5) @ return The total number
     * of colored cells
     */
    public long coloredCells(int n) {
        // For n=1, return 1
        if (n == 1) {
            return 1;
        }

        // Formula: 1 + 4 * (1 + 2 + ... + (n-1))
        // Sum of first (n-1) numbers = (n-1)*n/2
        long cells = 1 + 4 * ((long) (n - 1) * n / 2);

        return cells;
    }

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[][] testCases = {
            {1, 1}, // {input, expected output}
            {2, 5},
            {3, 13},
            {4, 25},
            {5, 41},
            {10, 181}
        };

        // Run test cases
        for (int[] test : testCases) {
            int input = test[0];
            long expected = test[1];
            long result = solution.coloredCells(input);

            System.out.printf("Input: n = %d\n", input);
            System.out.printf("Expected: %d\n", expected);
            System.out.printf("Output: %d\n", result);
            System.out.printf("Result: %s\n", result == expected ? "PASSED" : "FAILED");
            System.out.println("------------------------");
        }
    }
}
