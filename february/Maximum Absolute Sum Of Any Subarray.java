
/**
 * LeetCode Problem: Maximum Absolute Sum of Any Subarray
 *
 * Problem Description:
 * You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr]
 * is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 *
 * Approach:
 * 1. Use Kadane's algorithm to find both maximum and minimum sum subarrays
 * 2. Track both positive and negative running sums
 * 3. Return the maximum absolute value between maxSum and minSum
 *
 * Time Complexity: O(n) where n is the length of input array
 * Space Complexity: O(1) as we only use constant extra space
 */
class Solution {

    /**
     * Finds the maximum absolute sum of any subarray in the given array.
     *
     * @param nums the input integer array
     * @return the maximum absolute sum possible
     */
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, minSum = 0;
        int maxSoFar = 0, minSoFar = 0;

        for (int num : nums) {
            maxSoFar = Math.max(num, maxSoFar + num);
            maxSum = Math.max(maxSum, maxSoFar);

            minSoFar = Math.min(num, minSoFar + num);
            minSum = Math.min(minSum, minSoFar);
        }

        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }

    /**
     * Main method to test the solution with various test cases.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Array with both positive and negative numbers
        int[] test1 = {1, -3, 2, 3, -4};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + arrayToString(test1));
        System.out.println("Output: " + solution.maxAbsoluteSum(test1));
        System.out.println("Expected: 5\n");

        // Test Case 2: Array with all positive numbers
        int[] test2 = {2, 5, 1, 3};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + arrayToString(test2));
        System.out.println("Output: " + solution.maxAbsoluteSum(test2));
        System.out.println("Expected: 11\n");

        // Test Case 3: Array with all negative numbers
        int[] test3 = {-3, -5, -2, -1};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + arrayToString(test3));
        System.out.println("Output: " + solution.maxAbsoluteSum(test3));
        System.out.println("Expected: 11\n");

        // Test Case 4: Array with single element
        int[] test4 = {5};
        System.out.println("Test Case 4:");
        System.out.println("Input: " + arrayToString(test4));
        System.out.println("Output: " + solution.maxAbsoluteSum(test4));
        System.out.println("Expected: 5\n");
    }

    /**
     * Helper method to convert array to string for printing.
     *
     * @param arr the input array
     * @return string representation of the array
     */
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
