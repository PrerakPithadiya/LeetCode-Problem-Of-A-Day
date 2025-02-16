
/**
 * Solution for constructing the lexicographically largest valid sequence.
 *
 * Problem Description:
 * Given an integer n, find a sequence of integers of length 2n-1 such that:
 * 1. Every integer between 1 and n appears in the sequence exactly once for 1,
 *    and exactly twice for numbers from 2 to n.
 * 2. For every integer i (2 <= i <= n), the distance between the two occurrences of i is exactly i.
 * 3. The sequence should be lexicographically the largest among all possible valid sequences.
 *
 * Time Complexity: O(n!) - In worst case, we try all possible permutations
 * Space Complexity: O(n) - For recursion stack and boolean array
 */
import java.util.Arrays;

class Solution {

    /**
     * Constructs the lexicographically largest valid sequence for given number
     * n.
     *
     * @param n The input number (1 <= n <= 20)
     * @return An array representing the constructed sequence
     * @throws IllegalArgumentException if n is less than 1 or greater than 20
     */
    public int[] constructDistancedSequence(int n) {
        if (n < 1 || n > 20) {
            throw new IllegalArgumentException("n must be between 1 and 20");
        }

        // Length of the sequence will be 2*(n-1) + 1
        // Because numbers 2 to n appear twice, and 1 appears once
        int length = 2 * (n - 1) + 1;
        int[] result = new int[length];
        boolean[] used = new boolean[n + 1];

        // Start backtracking from index 0
        backtrack(0, n, result, used);
        return result;
    }

    /**
     * Recursive backtracking function to construct the sequence.
     *
     * @param pos Current position in the sequence
     * @param n The input number
     * @param result Array to store the sequence
     * @param used Boolean array to track used numbers
     * @return true if a valid sequence is found, false otherwise
     */
    private boolean backtrack(int pos, int n, int[] result, boolean[] used) {
        // If we've filled all positions, we've found a valid sequence
        if (pos == result.length) {
            return true;
        }

        // If this position is already filled, move to next position
        if (result[pos] != 0) {
            return backtrack(pos + 1, n, result, used);
        }

        // Try placing numbers from largest to smallest to get lexicographically largest sequence
        for (int num = n; num >= 1; num--) {
            if (used[num]) {
                continue;
            }

            if (num == 1) {
                // For number 1, we just need one position
                result[pos] = 1;
                used[1] = true;

                if (backtrack(pos + 1, n, result, used)) {
                    return true;
                }

                // Backtrack
                result[pos] = 0;
                used[1] = false;
            } else {
                // For numbers > 1, we need two positions with distance num
                if (pos + num < result.length && result[pos + num] == 0) {
                    result[pos] = num;
                    result[pos + num] = num;
                    used[num] = true;

                    if (backtrack(pos + 1, n, result, used)) {
                        return true;
                    }

                    // Backtrack
                    result[pos] = 0;
                    result[pos + num] = 0;
                    used[num] = false;
                }
            }
        }

        return false;
    }

    /**
     * Test cases for the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: n = 3
        // Expected: [3,1,2,3,2]
        int[] result1 = solution.constructDistancedSequence(3);
        System.out.println("Test Case 1: " + Arrays.toString(result1));

        // Test Case 2: n = 5
        // Expected: [5,3,1,4,3,5,2,4,2]
        int[] result2 = solution.constructDistancedSequence(5);
        System.out.println("Test Case 2: " + Arrays.toString(result2));

        // Test Case 3: n = 1
        // Expected: [1]
        int[] result3 = solution.constructDistancedSequence(1);
        System.out.println("Test Case 3: " + Arrays.toString(result3));

        // Test Case 4: Error case
        try {
            solution.constructDistancedSequence(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 4: Caught expected exception for n = 0");
        }
    }
}
