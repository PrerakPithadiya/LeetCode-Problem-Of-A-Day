
/**
 * Solution class to find missing and repeated values in a grid
 * LeetCode Problem: Find Missing and Repeated Values
 *
 * Problem Description:
 * Given a 0-indexed 2D integer matrix grid of size n * n with values in range [1, n^2].
 * Each integer appears exactly once except:
 * - a which appears twice
 * - b which is missing
 * Task is to find the repeating number (a) and missing number (b).
 *
 * Time Complexity: O(n²) where n is the size of grid
 * Space Complexity: O(n²) for frequency array
 */
class Solution {

    /**
     * Finds the repeated and missing values in the given grid
     *
     * @param grid 2D integer array of size n*n containing numbers from 1 to n^2
     * @return int array of size 2 where result[0] is repeated number and
     * result[1] is missing number
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] freq = new int[n * n + 1];

        // Count frequency of each number
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                freq[grid[i][j]]++;
            }
        }

        int[] result = new int[2];
        // Find repeated and missing numbers
        for (int i = 1; i <= n * n; i++) {
            if (freq[i] == 2) {
                result[0] = i;  // repeated number
            }
            if (freq[i] == 0) {
                result[1] = i;  // missing number
            }
        }

        return result;
    }

    /**
     * Main method to test the solution with example cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        int[][] grid1 = {{1, 3}, {2, 2}};
        int[] result1 = solution.findMissingAndRepeatedValues(grid1);
        System.out.println("Test Case 1:");
        System.out.println("Input: [[1,3],[2,2]]");
        System.out.println("Output: [" + result1[0] + "," + result1[1] + "]");
        System.out.println("Expected: [2,4]");
        System.out.println();

        // Test Case 2
        int[][] grid2 = {{9, 1, 7}, {8, 9, 2}, {3, 4, 6}};
        int[] result2 = solution.findMissingAndRepeatedValues(grid2);
        System.out.println("Test Case 2:");
        System.out.println("Input: [[9,1,7],[8,9,2],[3,4,6]]");
        System.out.println("Output: [" + result2[0] + "," + result2[1] + "]");
        System.out.println("Expected: [9,5]");
        System.out.println();

        // Additional Test Case
        int[][] grid3 = {{1, 1}, {3, 4}};
        int[] result3 = solution.findMissingAndRepeatedValues(grid3);
        System.out.println("Test Case 3:");
        System.out.println("Input: [[1,1],[3,4]]");
        System.out.println("Output: [" + result3[0] + "," + result3[1] + "]");
        System.out.println("Expected: [1,2]");
    }
}
