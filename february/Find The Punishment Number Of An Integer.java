
/**
 * Solution class for finding the punishment number of an integer.
 * The punishment number of n is the sum of i * i for all i from 1 to n where i * i can be partitioned
 * into a sequence of integers that sum to i.
 */
class Solution {

    /**
     * Calculates the punishment number for a given integer n.
     *
     * @param n The input integer (1 <= n <= 1000) @ return The punishment
     * number of n
     *
     * Example 1: Input: n = 10 Output: 182 Explanation: 1 is a punishment
     * number since 1 = 1 9 is a punishment number since 9 = 9 10 is a
     * punishment number since 100 = 10 + 0 + 0 Therefore, the punishment number
     * of 10 is 1 + 81 + 100 = 182
     *
     * Example 2: Input: n = 37 Output: 1478 Explanation: The punishment numbers
     * are 1, 9, 10, 36, 37
     */
    public int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (canPartition(i * i, i)) {
                sum += i * i;
            }
        }
        return sum;
    }

    /**
     * Helper function to check if a number can be partitioned to sum to target.
     *
     * @param num The number to be partitioned
     * @param target The target sum
     * @return true if num can be partitioned to sum to target, false otherwise
     */
    private boolean canPartition(int num, int target) {
        return canPartitionHelper(String.valueOf(num), 0, 0, target);
    }

    /**
     * Recursive helper function to try all possible partitions of a number.
     *
     * @param numStr The string representation of the number
     * @param index Current index in the string
     * @param currentSum Running sum of partitions
     * @param target Target sum to achieve
     * @return true if a valid partition is found, false otherwise
     */
    private boolean canPartitionHelper(String numStr, int index, int currentSum, int target) {
        // Base case: if we've reached the end of the string
        if (index == numStr.length()) {
            return currentSum == target;
        }

        // Try all possible partitions from current index
        long partitionSum = 0;
        for (int i = index; i < numStr.length(); i++) {
            // Build up the current partition
            partitionSum = partitionSum * 10 + (numStr.charAt(i) - '0');

            // Check for overflow
            if (partitionSum > Integer.MAX_VALUE) {
                break;
            }

            // If current sum plus this partition exceeds target, no need to continue
            if (currentSum + partitionSum > target) {
                break;
            }

            // Recursively try with this partition
            if (canPartitionHelper(numStr, i + 1, currentSum + (int) partitionSum, target)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Test cases for the Solution class
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        assert solution.punishmentNumber(10) == 182 : "Test case 1 failed";

        // Test case 2
        assert solution.punishmentNumber(37) == 1478 : "Test case 2 failed";

        // Test case 3: Edge case - minimum input
        assert solution.punishmentNumber(1) == 1 : "Test case 3 failed";

        // Test case 4: Testing specific numbers
        assert solution.canPartition(100, 10) == true : "Test case 4 failed"; // 100 = 10 + 0 + 0
        assert solution.canPartition(36, 6) == false : "Test case 5 failed"; // 36 cannot be partitioned to sum to 6

        System.out.println("All test cases passed!");
    }
}
