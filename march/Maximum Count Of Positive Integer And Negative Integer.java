
/**
 * LeetCode Problem: Maximum Count of Positive Integer and Negative Integer
 *
 * This class provides a solution to find the maximum between the count of positive
 * and negative integers in a sorted array.
 *
 * Time Complexity: O(log n) using binary search
 * Space Complexity: O(1) using constant extra space
 *
 * @author Prerak Pithadiya
 */
class Solution {

    /**
     * Main method to run test cases
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        int[] nums1 = {-2, -1, -1, 1, 2, 3};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + arrayToString(nums1));
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output: " + solution.maximumCount(nums1));
        System.out.println();

        // Test Case 2
        int[] nums2 = {-3, -2, -1, 0, 0, 1, 2};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + arrayToString(nums2));
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output: " + solution.maximumCount(nums2));
        System.out.println();

        // Test Case 3
        int[] nums3 = {5, 20, 66, 1314};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + arrayToString(nums3));
        System.out.println("Expected Output: 4");
        System.out.println("Actual Output: " + solution.maximumCount(nums3));
    }

    /**
     * Helper method to convert array to string for pretty printing
     *
     * @param arr input array
     * @return string representation of array
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

    /**
     * Finds the maximum between the count of positive and negative integers
     *
     * @param nums sorted array in non-decreasing order
     * @return maximum of count of positive and negative integers
     */
    public int maximumCount(int[] nums) {
        // Find position of first positive number
        int firstPositive = findFirstPositive(nums);

        // Find position of first non-negative number (0 or positive)
        int firstNonNegative = findFirstNonNegative(nums);

        // Count of negative numbers = firstNonNegative
        int negatives = firstNonNegative;

        // Count of positive numbers = length - firstPositive
        int positives = nums.length - firstPositive;

        return Math.max(negatives, positives);
    }

    /**
     * Binary search to find the index of first positive number
     *
     * @param nums input array
     * @return index of first positive number
     */
    private int findFirstPositive(int[] nums) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * Binary search to find the index of first non-negative number
     *
     * @param nums input array
     * @return index of first non-negative number
     */
    private int findFirstNonNegative(int[] nums) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
