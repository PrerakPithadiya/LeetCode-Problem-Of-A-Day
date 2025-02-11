
/**
 * Solution class for removing all occurrences of a substring from a string.
 * LeetCode Problem: Remove All Occurrences of a Substring
 */
class Solution {

    /**
     * Removes all occurrences of a given substring from the input string.
     *
     * @param s The input string from which to remove occurrences
     * @param part The substring to be removed from the input string
     * @return A new string with all occurrences of 'part' removed
     * @throws NullPointerException if either s or part is null
     *
     * Time Complexity: O(n * m) where n is length of s and m is length of part
     * Space Complexity: O(n) for StringBuilder
     *
     * Example 1: Input: s = "daabcbaabcbc", part = "abc" Output: "dab"
     * Explanation: - Remove "abc" starting at index 2 -> "dabaabcbc" - Remove
     * "abc" starting at index 4 -> "dababc" - Remove "abc" starting at index 3
     * -> "dab"
     *
     * Example 2: Input: s = "axxxxyyyyb", part = "xy" Output: "ab"
     */
    public String removeOccurrences(String s, String part) {
        // Input validation
        if (s == null || part == null) {
            throw new NullPointerException("Input string and part cannot be null");
        }
        if (part.isEmpty()) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s);
        int partLen = part.length();

        // Keep removing part while it exists in the string
        int index;
        while ((index = sb.indexOf(part)) != -1) {
            sb.delete(index, index + partLen);
        }

        return sb.toString();
    }

    /**
     * Test cases to verify the functionality
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic case
        assert solution.removeOccurrences("daabcbaabcbc", "abc").equals("dab") : "Test case 1 failed";

        // Test case 2: Multiple consecutive occurrences
        assert solution.removeOccurrences("axxxxyyyyb", "xy").equals("ab") : "Test case 2 failed";

        // Test case 3: No occurrence
        assert solution.removeOccurrences("hello", "world").equals("hello") : "Test case 3 failed";

        // Test case 4: Empty string
        assert solution.removeOccurrences("", "abc").equals("") : "Test case 4 failed";

        // Test case 5: Empty part
        assert solution.removeOccurrences("hello", "").equals("hello") : "Test case 5 failed";

        // Test case 6: Single character
        assert solution.removeOccurrences("aaa", "a").equals("") : "Test case 6 failed";

        System.out.println("All test cases passed!");
    }
}
