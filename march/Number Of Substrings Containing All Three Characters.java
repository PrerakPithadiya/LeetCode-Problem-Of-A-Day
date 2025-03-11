
/**
 * LeetCode Problem: Number of Substrings Containing All Three Characters
 * ---------------------------------------------------------------------
 * Given a string containing only characters a, b and c, find the number of substrings
 * that contain at least one occurrence of all three characters.
 *
 * Algorithm:
 * - Use an array to track the last position of each character (a, b, c)
 * - For each position, if all characters are present, add valid substrings count
 * - Valid substrings count = minimum last position + 1
 *
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use a fixed size array
 */
class Solution {

    /**
     * Counts substrings containing all three characters a, b, and c.
     *
     * @param s Input string containing only characters a, b, and c
     * @return Number of valid substrings
     */
    public int numberOfSubstrings(String s) {
        int[] last = {-1, -1, -1}; // Last positions of a, b, c
        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;

            // If we have found all three characters
            if (last[0] != -1 && last[1] != -1 && last[2] != -1) {
                // Add number of substrings starting from min position
                result += Math.min(Math.min(last[0], last[1]), last[2]) + 1;
            }
        }

        return result;
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String[] testInputs = {
            "abcabc", // Expected: 10
            "aaacb", // Expected: 3
            "abc", // Expected: 1
            "abcab", // Expected: 3
            "aaaabc" // Expected: 1
        };

        System.out.println("Running test cases...\n");
        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            int result = solution.numberOfSubstrings(input);

            System.out.println("Test Case " + (i + 1));
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Output: " + result);
            System.out.println("----------------------------------------");
        }
    }
}
