
import java.util.ArrayList;
import java.util.List;

/**
 * Solution class for checking if two strings can be made equal with one swap
 * operation LeetCode Problem: Check If One String Swap Can Make Strings Equal
 */
class Solution {

    /**
     * Determines if two strings can be made equal by swapping exactly one pair
     * of characters in one string.
     *
     * @param s1 The first input string
     * @param s2 The second input string
     * @return true if strings can be made equal with one swap, false otherwise
     *
     * Time Complexity: O(n) where n is the length of the strings Space
     * Complexity: O(1) as we store at most 2 indices
     *
     * Example 1: Input: s1 = "bank", s2 = "kanb" Output: true Explanation: We
     * can swap 'b' and 'k' to transform s1 into s2
     *
     * Example 2: Input: s1 = "attack", s2 = "defend" Output: false Explanation:
     * It's impossible to make them equal with one string swap
     *
     * Example 3: Input: s1 = "kelb", s2 = "kelb" Output: true Explanation:
     * Strings are already equal, no swap needed
     */
    public boolean areAlmostEqual(String s1, String s2) {
        // If the strings are already equal, return true
        if (s1.equals(s2)) {
            return true;
        }

        int n = s1.length();
        List<Integer> diffIndices = new ArrayList<>();

        // Find all the indices where the characters differ
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffIndices.add(i);
            }
        }

        // If there are more than 2 differences, return false
        if (diffIndices.size() != 2) {
            return false;
        }

        // Get the two differing indices
        int idx1 = diffIndices.get(0);
        int idx2 = diffIndices.get(1);

        // Check if swapping makes the strings equal
        return s1.charAt(idx1) == s2.charAt(idx2) && s1.charAt(idx2) == s2.charAt(idx1);
    }

    /**
     * Test cases to verify the functionality of areAlmostEqual method
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Strings that can be made equal with one swap
        assert solution.areAlmostEqual("bank", "kanb") == true;

        // Test Case 2: Strings that are already equal
        assert solution.areAlmostEqual("hello", "hello") == true;

        // Test Case 3: Strings that cannot be made equal with one swap
        assert solution.areAlmostEqual("attack", "defend") == false;

        // Test Case 4: Strings with more than two different positions
        assert solution.areAlmostEqual("abcd", "dcba") == false;

        // Test Case 5: Strings with same characters but wrong positions
        assert solution.areAlmostEqual("aa", "aa") == true;

        System.out.println("All test cases passed!");
    }
}
