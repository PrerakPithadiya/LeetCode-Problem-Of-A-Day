
/**
 * Solution for LeetCode problem: Minimize XOR
 *
 * Problem Description:
 * Given two integers num1 and num2, find the integer x that:
 * - Has the same number of set bits (1's in binary representation) as num2
 * - Minimizes the value of num1 XOR x
 * - If there are multiple such x's, return the smallest one
 *
 * Approach:
 * 1. Count set bits in num2 to determine target number of 1's needed
 * 2. If num1 has same number of set bits as num2, return num1
 * 3. Otherwise, construct x using three passes:
 *    - First pass: Match 1's with num1 from left to right (most significant bits)
 *    - Second pass: Add remaining 1's from right to left where num1 has 0's
 *    - Third pass: Remove excess 1's from right to left if needed
 *
 * Time Complexity: O(1) - constant time as we always process 32 bits
 * Space Complexity: O(1) - constant space
 */
class Solution {

    /**
     * Finds the integer x that has the same number of set bits as num2 and
     * minimizes num1 XOR x
     *
     * @param num1 The first integer to consider
     * @param num2 The second integer whose number of set bits must match the
     * result
     * @return The integer x that satisfies the conditions
     */
    public int minimizeXor(int num1, int num2) {
        // Count set bits in num2 - this is how many 1's we need in our answer
        int targetBits = Integer.bitCount(num2);

        // If both numbers have same number of set bits, num1 is the answer
        if (Integer.bitCount(num1) == targetBits) {
            return num1;
        }

        int x = 0;

        // First pass: Set 1's in x where num1 has 1's, from left to right
        // This ensures we minimize XOR by matching 1's where possible
        int remainingBits = targetBits;
        for (int i = 31; i >= 0 && remainingBits > 0; i--) {
            if ((num1 & (1 << i)) != 0) {
                x |= (1 << i);
                remainingBits--;
            }
        }

        // Second pass: If we still need more 1's, add them from right to left
        // in positions where num1 has 0's
        if (remainingBits > 0) {
            for (int i = 0; i < 32 && remainingBits > 0; i++) {
                if ((num1 & (1 << i)) == 0 && (x & (1 << i)) == 0) {
                    x |= (1 << i);
                    remainingBits--;
                }
            }
        } // Third pass: If we have too many 1's, remove them from right to left
        else if (remainingBits < 0) {
            for (int i = 0; i < 32 && remainingBits < 0; i++) {
                if ((x & (1 << i)) != 0) {
                    x ^= (1 << i);
                    remainingBits++;
                }
            }
        }

        return x;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Same number of set bits
        assert solution.minimizeXor(3, 5) == 3; // num1 = 11, num2 = 101 (both have 2 set bits)

        // Test Case 2: Need more set bits
        assert solution.minimizeXor(1, 12) == 3; // num1 = 1, num2 = 1100 (need 2 set bits)

        // Test Case 3: Need fewer set bits
        assert solution.minimizeXor(7, 1) == 1; // num1 = 111, num2 = 1 (need 1 set bit)

        // Test Case 4: Large numbers
        assert solution.minimizeXor(65, 84) == 67; // num1 = 1000001, num2 = 1010100

        // Test Case 5: Zero case
        assert solution.minimizeXor(1, 0) == 0; // num1 = 1, num2 = 0 (need 0 set bits)

        System.out.println("All test cases passed!");
    }
}
