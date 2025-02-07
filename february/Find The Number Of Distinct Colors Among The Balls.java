
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for "Find The Number Of Distinct Colors Among The Balls" problem
 *
 * Problem Description: Given a series of queries where each query colors a ball
 * with a specific color, determine the number of distinct colors after each
 * operation.
 *
 * Time Complexity: O(n) where n is the number of queries Space Complexity: O(m)
 * where m is the number of distinct balls colored
 */
class Solution {

    /**
     * Processes color queries and returns the count of distinct colors after
     * each operation
     *
     * @param limit The maximum number of balls (not used in current
     * implementation but kept for future use)
     * @param queries 2D array where queries[i] = [x, y] means ball x is colored
     * with color y
     * @return Array containing the count of distinct colors after each query
     */
    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ballColor = new HashMap<>(); // Tracks color of each ball that has been colored
        Map<Integer, Integer> colorCount = new HashMap<>(); // Tracks the frequency of each color
        int distinct = 0;
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];

            // Retrieve the old color, defaulting to 0 (uncolored)
            int oldColor = ballColor.getOrDefault(x, 0);

            if (oldColor == y) {
                result[i] = distinct;
                continue;
            }

            // Decrement the old color's count if it was previously colored (oldColor != 0)
            if (oldColor != 0) {
                int cnt = colorCount.get(oldColor);
                cnt--;
                if (cnt == 0) {
                    colorCount.remove(oldColor);
                    distinct--;
                } else {
                    colorCount.put(oldColor, cnt);
                }
            }

            // Increment the new color's count
            int newCnt = colorCount.getOrDefault(y, 0);
            if (newCnt == 0) {
                distinct++;
            }
            colorCount.put(y, newCnt + 1);

            // Update the ball's color
            ballColor.put(x, y);

            result[i] = distinct;
        }

        return result;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic test with different colors
        int[][] queries1 = {{1, 1}, {2, 2}, {3, 3}};
        int[] result1 = solution.queryResults(3, queries1);
        assert result1[2] == 3 : "Test case 1 failed";

        // Test Case 2: Same color multiple times
        int[][] queries2 = {{1, 1}, {2, 1}, {3, 1}};
        int[] result2 = solution.queryResults(3, queries2);
        assert result2[2] == 1 : "Test case 2 failed";

        // Test Case 3: Changing colors
        int[][] queries3 = {{1, 1}, {1, 2}, {1, 3}};
        int[] result3 = solution.queryResults(3, queries3);
        assert result3[2] == 1 : "Test case 3 failed";

        // Test Case 4: Empty query
        int[][] queries4 = {};
        int[] result4 = solution.queryResults(0, queries4);
        assert result4.length == 0 : "Test case 4 failed";

        System.out.println("All test cases passed!");
    }
}
