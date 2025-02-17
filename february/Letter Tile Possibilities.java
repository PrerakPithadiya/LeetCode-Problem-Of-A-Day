
import java.util.HashSet;
import java.util.Set;

/**
 * Solution class for generating all possible letter tile sequences. This class
 * solves the problem of finding the number of distinct sequences that can be
 * formed using the given tiles.
 */
class Solution {

    /**
     * Set to store all unique sequences
     */
    private Set<String> sequences;

    /**
     * Calculates the number of possible distinct sequences that can be formed
     * using the given tiles.
     *
     * @param tiles String containing the available letter tiles
     * @return int Number of possible distinct sequences
     */
    public int numTilePossibilities(String tiles) {
        sequences = new HashSet<>();
        char[] chars = tiles.toCharArray();
        boolean[] used = new boolean[chars.length];

        // Generate all possible sequences
        backtrack(chars, used, new StringBuilder());

        // Remove empty string if it exists
        sequences.remove("");

        return sequences.size();
    }

    /**
     * Recursive backtracking method to generate all possible sequences.
     *
     * @param chars Array of available characters
     * @param used Boolean array tracking used characters
     * @param current StringBuilder containing the current sequence being built
     */
    private void backtrack(char[] chars, boolean[] used, StringBuilder current) {
        // Add current sequence
        sequences.add(current.toString());

        // Try adding each unused character
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.append(chars[i]);

                backtrack(chars, used, current);

                // Backtrack by removing the last character
                current.setLength(current.length() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * Test cases to verify the functionality of the solution.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic test with "AAB"
        String test1 = "AAB";
        int result1 = solution.numTilePossibilities(test1);
        assert result1 == 8 : "Test case 1 failed";

        // Test Case 2: Single character
        String test2 = "A";
        int result2 = solution.numTilePossibilities(test2);
        assert result2 == 1 : "Test case 2 failed";

        // Test Case 3: All different characters
        String test3 = "ABC";
        int result3 = solution.numTilePossibilities(test3);
        assert result3 == 15 : "Test case 3 failed";

        // Test Case 4: Empty string
        String test4 = "";
        int result4 = solution.numTilePossibilities(test4);
        assert result4 == 0 : "Test case 4 failed";

        // Test Case 5: All same characters
        String test5 = "ZZZ";
        int result5 = solution.numTilePossibilities(test5);
        assert result5 == 3 : "Test case 5 failed";

        System.out.println("All test cases passed successfully!");
    }
}
