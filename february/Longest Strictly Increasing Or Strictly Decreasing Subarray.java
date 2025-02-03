
/**
 * Solution class for finding the longest strictly monotonic (increasing or decreasing) subarray.
 *
 * Problem Description:
 * Given an integer array nums, find the length of the longest strictly monotonic subarray.
 * A subarray is strictly monotonic if it is either strictly increasing or strictly decreasing.
 *
 * Time Complexity: O(n) where n is the length of the input array
 * Space Complexity: O(1) as we only use constant extra space
 */
class Solution {

    /**
     * Finds the length of the longest strictly monotonic subarray.
     *
     * @param nums the input array of integers
     * @return the length of the longest strictly increasing or strictly
     * decreasing subarray
     */
    public int longestMonotonicSubarray(int[] nums) {
        // Handle empty array case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLen = 1;  // Maximum length of monotonic subarray
        int incLen = 1;  // Current length of increasing subarray
        int decLen = 1;  // Current length of decreasing subarray

        // Iterate through the array starting from second element
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                // Current element is greater than previous - extend increasing sequence
                incLen++;
                decLen = 1;
            } else if (nums[i] < nums[i - 1]) {
                // Current element is smaller than previous - extend decreasing sequence
                decLen++;
                incLen = 1;
            } else {
                // Current element equals previous - reset both sequences
                incLen = 1;
                decLen = 1;
            }
            maxLen = Math.max(maxLen, Math.max(incLen, decLen));
        }

        return maxLen;
    }

    /**
     * Test cases to verify the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Strictly increasing sequence
        assert solution.longestMonotonicSubarray(new int[]{1, 2, 3, 4, 5}) == 5;

        // Test Case 2: Strictly decreasing sequence
        assert solution.longestMonotonicSubarray(new int[]{5, 4, 3, 2, 1}) == 5;

        // Test Case 3: Mixed sequence
        assert solution.longestMonotonicSubarray(new int[]{1, 2, 3, 2, 1}) == 3;

        // Test Case 4: Sequence with equal elements
        assert solution.longestMonotonicSubarray(new int[]{1, 2, 2, 3}) == 2;

        // Test Case 5: Empty array
        assert solution.longestMonotonicSubarray(new int[]{}) == 0;

        // Test Case 6: Single element
        assert solution.longestMonotonicSubarray(new int[]{1}) == 1;

        // Test Case 7: Null array
        assert solution.longestMonotonicSubarray(null) == 0;

        System.out.println("All test cases passed!");
    }
}
