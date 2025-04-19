import java.util.Arrays;

/**
 * LeetCode Problem 2563: Count the Number of Fair Pairs
 * 
 * Problem Description:
 * Given a 0-indexed integer array nums of size n and two integers lower and upper,
 * return the number of fair pairs.
 * A pair (i, j) is fair if:git add CountFairPairs_2563.java
 git commit -m "Add solution for LeetCode problem 2563"
 git push origin main
 * - 0 <= i < j < n, and
 * - lower <= nums[i] + nums[j] <= upper
 * 
 * Time Complexity: O(n log n) where n is the length of the array
 * Space Complexity: O(1) auxiliary space (excluding the sorting space)
 * 
 * @author Prerak Pithadiya
 * @date April 19, 2025
 */
class CountFairPairs_2563 {
    /**
     * Counts the number of fair pairs in the array.
     * 
     * @param nums the input array
     * @param lower the lower bound for the sum
     * @param upper the upper bound for the sum
     * @return the number of fair pairs
     */
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return countLessThan(nums, upper + 1) - countLessThan(nums, lower);
    }
    
    /**
     * Helper method to count pairs where nums[i] + nums[j] < limit.
     * Uses a two-pointer approach for optimal performance.
     * 
     * @param nums the sorted input array
     * @param limit the upper limit for the sum
     * @return count of pairs with sum less than limit
     */
    private long countLessThan(int[] nums, int limit) {
        long count = 0;
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] + nums[right] < limit) {
                // If nums[left] + nums[right] < limit, then all pairs with 
                // indices (left, left+1), (left, left+2), ..., (left, right) are valid
                count += right - left;
                left++;
            } else {
                // If the sum is too large, move right pointer to find smaller sum
                right--;
            }
        }
        
        return count;
    }
    
    /**
     * Main method to test the solution with example cases.
     */
    public static void main(String[] args) {
        CountFairPairs_2563 solution = new CountFairPairs_2563();
        
        // Test Case 1
        int[] nums1 = {0, 1, 7, 4, 4, 5};
        int lower1 = 3;
        int upper1 = 6;
        System.out.println("Example 1:");
        System.out.println("Input: nums = [0, 1, 7, 4, 4, 5], lower = 3, upper = 6");
        System.out.println("Output: " + solution.countFairPairs(nums1, lower1, upper1));
        System.out.println("Expected: 6");
        System.out.println("Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).");
        System.out.println();
        
        // Test Case 2
        int[] nums2 = {1, 7, 9, 2, 5};
        int lower2 = 11;
        int upper2 = 11;
        System.out.println("Example 2:");
        System.out.println("Input: nums = [1, 7, 9, 2, 5], lower = 11, upper = 11");
        System.out.println("Output: " + solution.countFairPairs(nums2, lower2, upper2));
        System.out.println("Expected: 1");
        System.out.println("Explanation: There is a single fair pair: (2,3).");
        System.out.println();
        
        // Additional Test Case
        int[] nums3 = {-1, -2, -3, -4, -5};
        int lower3 = -6;
        int upper3 = -2;
        System.out.println("Example 3 (Additional):");
        System.out.println("Input: nums = [-1, -2, -3, -4, -5], lower = -6, upper = -2");
        System.out.println("Output: " + solution.countFairPairs(nums3, lower3, upper3));
        System.out.println("Expected: 6");
        System.out.println("Explanation: The fair pairs are: (0,1), (0,2), (0,3), (1,2), (1,3), and (2,3).");
    }
}
