
/**
 * Solution for LeetCode problem: Maximum Ascending Subarray Sum
 *
 * Problem Description:
 * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
 * A subarray is defined as a contiguous sequence of numbers within the array.
 * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1.
 *
 * Approach:
 * 1. Initialize maxSum and currentSum with the first element
 * 2. Iterate through the array starting from index 1
 * 3. If current number is greater than previous number, add it to currentSum
 * 4. Otherwise, reset currentSum to current number
 * 5. Update maxSum if currentSum is greater
 *
 * Time Complexity: O(n) where n is the length of input array
 * Space Complexity: O(1) as we only use constant extra space
 */
class Solution {

    /**
     * Finds the maximum sum of an ascending subarray in the given array.
     *
     * @param nums the input array of positive integers
     * @return the maximum sum of an ascending subarray
     * @throws IllegalArgumentException if the input array is null or empty
     */
    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentSum += nums[i];
            } else {
                currentSum = nums[i];
            }

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic ascending sequence
        assert solution.maxAscendingSum(new int[]{1, 2, 3, 2, 1}) == 6 : "Test case 1 failed";

        // Test case 2: Multiple ascending sequences
        assert solution.maxAscendingSum(new int[]{10, 20, 30, 5, 10, 50}) == 65 : "Test case 2 failed";

        // Test case 3: Single element
        assert solution.maxAscendingSum(new int[]{5}) == 5 : "Test case 3 failed";

        // Test case 4: Non-ascending sequence
        assert solution.maxAscendingSum(new int[]{3, 2, 1}) == 3 : "Test case 4 failed";

        // Test case 5: Equal elements
        assert solution.maxAscendingSum(new int[]{1, 1, 1}) == 1 : "Test case 5 failed";

        System.out.println("All test cases passed!");
    }
}
