
/**
 * Problem: Alternating Groups II
 *
 * Description:
 * Given a circle of red and blue tiles represented by an array 'colors' where:
 * - colors[i] == 0 means tile i is red
 * - colors[i] == 1 means tile i is blue
 * Find the number of alternating groups of size k where colors alternate.
 *
 * Approach:
 * 1. Extend the array to handle circular nature
 * 2. Use sliding window technique to find valid alternating sequences
 * 3. Count sequences of length k with alternating colors
 *
 * Time Complexity: O(n), where n is the length of input array
 * Space Complexity: O(n + k) for the extended array
 */
class Solution {

    /**
     * Counts the number of alternating groups of size k in the circular array.
     *
     * @param colors Array representing colors of tiles (0 for red, 1 for blue)
     * @param k Size of alternating groups to find
     * @return Number of valid alternating groups
     */
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int[] extendedColors = new int[colors.length + k - 1];
        // Extend the array to handle circular sequences
        System.arraycopy(colors, 0, extendedColors, 0, colors.length);
        System.arraycopy(colors, 0, extendedColors, colors.length, k - 1);

        int length = extendedColors.length;
        int result = 0;
        // Initialize the bounds of the sliding window
        int left = 0;
        int right = 1;

        while (right < length) {
            // Check if the current color is the same as the last one
            if (extendedColors[right] == extendedColors[right - 1]) {
                // Pattern breaks, reset window from the current position
                left = right;
                right++;
                continue;
            }

            // Expand the window to the right
            right++;

            // Skip counting sequence if its length is less than k
            if (right - left < k) {
                continue;
            }

            // Record a valid sequence and shrink window from the left to search for more
            result++;
            left++;
        }

        return result;
    }

    /**
     * Main method to test the solution with example cases.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        int[] colors1 = {0, 1, 0, 1, 0};
        int k1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("Input: colors = [0,1,0,1,0], k = 3");
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output: " + solution.numberOfAlternatingGroups(colors1, k1));
        System.out.println();

        // Test Case 2
        int[] colors2 = {0, 1, 0, 0, 1, 0, 1};
        int k2 = 6;
        System.out.println("Test Case 2:");
        System.out.println("Input: colors = [0,1,0,0,1,0,1], k = 6");
        System.out.println("Expected Output: 2");
        System.out.println("Actual Output: " + solution.numberOfAlternatingGroups(colors2, k2));
        System.out.println();

        // Test Case 3
        int[] colors3 = {1, 1, 0, 1};
        int k3 = 4;
        System.out.println("Test Case 3:");
        System.out.println("Input: colors = [1,1,0,1], k = 4");
        System.out.println("Expected Output: 0");
        System.out.println("Actual Output: " + solution.numberOfAlternatingGroups(colors3, k3));
    }
}
