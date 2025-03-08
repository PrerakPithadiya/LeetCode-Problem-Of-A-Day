
/**
 * LeetCode Problem: Minimum Recolors to Get K Consecutive Black Blocks
 *
 * Problem Description:
 * Given a string of blocks represented by 'W' (white) and 'B' (black), find the minimum
 * number of operations needed to create k consecutive black blocks. In one operation,
 * you can change a white block to black.
 *
 * Time Complexity: O(n) where n is the length of the blocks string
 * Space Complexity: O(1) as only constant extra space is used
 *
 * @author Prerak Pithadiya
 */
class Solution {

    /**
     * Finds the minimum number of operations needed to get k consecutive black
     * blocks
     *
     * @param blocks String representing the blocks ('W' for white, 'B' for
     * black)
     * @param k The desired number of consecutive black blocks
     * @return The minimum number of operations needed
     */
    public int minimumRecolors(String blocks, int k) {
        int minOperations;
        int currentWhites = 0;

        // Count white blocks in first window of size k
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                currentWhites++;
            }
        }

        // Update minimum operations for first window
        minOperations = currentWhites;

        // Slide the window and count minimum operations
        for (int i = k; i < blocks.length(); i++) {
            // Remove contribution of leftmost character
            if (blocks.charAt(i - k) == 'W') {
                currentWhites--;
            }
            // Add contribution of current character
            if (blocks.charAt(i) == 'W') {
                currentWhites++;
            }

            minOperations = Math.min(minOperations, currentWhites);
        }

        return minOperations;
    }

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        String blocks1 = "WBBWWBBWBW";
        int k1 = 7;
        System.out.println("Test Case 1:");
        System.out.println("Input: blocks = \"" + blocks1 + "\", k = " + k1);
        System.out.println("Output: " + solution.minimumRecolors(blocks1, k1));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 2
        String blocks2 = "WBWBBBW";
        int k2 = 2;
        System.out.println("Test Case 2:");
        System.out.println("Input: blocks = \"" + blocks2 + "\", k = " + k2);
        System.out.println("Output: " + solution.minimumRecolors(blocks2, k2));
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 3 (Edge case - all whites)
        String blocks3 = "WWWWW";
        int k3 = 3;
        System.out.println("Test Case 3:");
        System.out.println("Input: blocks = \"" + blocks3 + "\", k = " + k3);
        System.out.println("Output: " + solution.minimumRecolors(blocks3, k3));
        System.out.println("Expected: 3");
        System.out.println();
    }
}
