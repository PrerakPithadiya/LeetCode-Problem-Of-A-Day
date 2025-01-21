
/**
 * Solution for LeetCode problem: First Completely Painted Row or Column
 *
 * Problem Description:
 * Given a 0-indexed array arr and a 2D matrix mat, find the earliest index i where either:
 * - A row is completely painted (all elements in the row are marked)
 * - A column is completely painted (all elements in the column are marked)
 *
 * Approach:
 * 1. Use bit manipulation to efficiently store positions of elements
 * 2. Store row in lower 16 bits and column in upper 16 bits
 * 3. Track count of painted cells in each row and column
 * 4. Return first index where any row count equals columns or column count equals rows
 *
 * Time Complexity: O(m*n) for initialization + O(k) for processing array, where k is arr.length
 * Space Complexity: O(m*n) for position array + O(m+n) for row/column counters
 */
class Solution {

    /**
     * Finds the first index where a row or column is completely painted.
     *
     * @param arr The input array containing 1-based numbers corresponding to
     * matrix positions
     * @param mat The input matrix containing the original positions of numbers
     * @return The earliest index where a row or column is completely painted
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Store positions in a single array using bit manipulation
        // Use 16 bits for row (0-15) and 16 bits for column (16-31)
        int[] pos = new int[m * n + 1];

        // Pack row and column into single integer
        // row in lower 16 bits, col in upper 16 bits
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pos[mat[i][j]] = i | (j << 16);
            }
        }

        // Use int arrays instead of byte arrays to avoid overflow
        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int i = 0; i < arr.length; i++) {
            // Extract row and column using bit operations
            int p = pos[arr[i]];
            int row = p & 0xFFFF;
            int col = p >>> 16;

            // Check if we've completed a row or column
            if (++rows[row] == n || ++cols[col] == m) {
                return i;
            }
        }

        return arr.length - 1;  // Changed from -1 to match problem constraints
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: 2x3 matrix
        int[] arr1 = {1, 3, 4, 2, 5, 6};
        int[][] mat1 = {{1, 4}, {2, 3}, {5, 6}};
        assert solution.firstCompleteIndex(arr1, mat1) == 2;

        // Test Case 2: 3x3 matrix
        int[] arr2 = {2, 8, 7, 4, 1, 3, 5, 6, 9};
        int[][] mat2 = {{3, 2, 5}, {1, 4, 6}, {8, 7, 9}};
        assert solution.firstCompleteIndex(arr2, mat2) == 3;

        // Test Case 3: 1x1 matrix
        int[] arr3 = {1};
        int[][] mat3 = {{1}};
        assert solution.firstCompleteIndex(arr3, mat3) == 0;

        // Test Case 4: 2x2 matrix
        int[] arr4 = {1, 4, 2, 3};
        int[][] mat4 = {{1, 2}, {3, 4}};
        assert solution.firstCompleteIndex(arr4, mat4) == 2;

        System.out.println("All test cases passed!");
    }
}
