
/**
 * Solution for LeetCode problem: Neighboring Bitwise XOR
 *
 * Problem Description:
 * Given a 0-indexed array derived with length n, determine if there exists an array original
 * such that derived[i] = original[i] XOR original[i+1] for all i from 0 to n-1 (inclusive).
 * Note that original[n] = original[0].
 *
 * Approach:
 * 1. The key observation is that in a valid array, each element from the original array
 *    appears exactly twice in the XOR operations that create the derived array.
 * 2. Due to the cyclic nature (original[n] = original[0]), if we XOR all elements in
 *    the derived array, the result must be 0 for a valid original array to exist.
 * 3. This is because:
 *    - Each original[i] appears in derived[i-1] and derived[i]
 *    - XOR of same number is 0 (a⊕a = 0)
 *    - XOR is associative and commutative
 *
 * Time Complexity: O(n) where n is the length of derived array
 * Space Complexity: O(1) as we only use a single variable
 */
class Solution {

    /**
     * Determines if a valid original array exists for the given derived array.
     *
     * @param derived The input array where derived[i] = original[i] XOR
     * original[i+1]
     * @return true if a valid original array exists, false otherwise
     */
    public boolean doesValidArrayExist(int[] derived) {
        // We can observe that the XOR sum of derived array must be 0
        // because each element from original array appears exactly twice
        int xorSum = 0;
        for (int num : derived) {
            xorSum ^= num;
        }

        // If XOR sum is not 0, no valid original array can exist
        return xorSum == 0;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Valid case with [1,1]
        // Possible original array: [0,1,0]
        assert solution.doesValidArrayExist(new int[]{1, 1}) == true;

        // Test Case 2: Invalid case with [1,1,0]
        // No valid original array exists
        assert solution.doesValidArrayExist(new int[]{1, 1, 0}) == false;

        // Test Case 3: Valid case with [1,0,1]
        // Possible original array: [0,1,1,0]
        assert solution.doesValidArrayExist(new int[]{1, 0, 1}) == true;

        // Test Case 4: Empty array (edge case)
        assert solution.doesValidArrayExist(new int[]{}) == true;

        // Test Case 5: Single element array
        assert solution.doesValidArrayExist(new int[]{0}) == true;

        System.out.println("All test cases passed!");
    }
}
